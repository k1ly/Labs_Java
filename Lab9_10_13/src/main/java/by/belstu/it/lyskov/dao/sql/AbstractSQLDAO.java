package by.belstu.it.lyskov.dao.sql;

import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import by.belstu.it.lyskov.dao.sql.builder.Builder;
import by.belstu.it.lyskov.dao.sql.builder.BuilderProvider;
import by.belstu.it.lyskov.util.connection.ConnectionPool;

import java.sql.*;
import java.util.*;

public abstract class AbstractSQLDAO<T> {
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String VALUES = " VALUES ";
    public static final String SELECT = "SELECT ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String AND = " AND ";
    public static final String DELETE = "DELETE";

    public String tableName;

    public abstract ParameterMap takeFields(T object);

    public abstract String getColumns();

    protected Integer insertEntity(ParameterMap parameters) throws SQLException {
        int generatedId = 0;
        if (parameters.size() > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = null;
            try {
                String sql = makeInsertStatement(parameters.getParameters());
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prepare(preparedStatement, parameters.getValues());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next())
                    generatedId = resultSet.getInt(1);
            } finally {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connectionPool.releaseConnection(connection);
            }
        }
        return generatedId;
    }

    protected int deleteEntity(ParameterMap deleteId) throws SQLException {
        int deletedRowAmount = 0;
        if (deleteId.size() > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            try (Statement statement = connection.createStatement()) {
                String sql = DELETE + FROM + tableName + makeQueryCondition(deleteId.getParameters());
                deletedRowAmount = statement.executeUpdate(sql);
            } finally {
                if (connection != null)
                    connectionPool.releaseConnection(connection);
            }
        }
        return deletedRowAmount;
    }

    protected String makeQuery() {
        return SELECT + getColumns() + FROM + tableName;
    }

    protected String makeQueryCondition(Map<String, Object> parameters) {
        StringBuilder sqlQuery = new StringBuilder().append(WHERE).append("(");
        if (parameters != null)
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                sqlQuery.append(entry.getKey()).append("=")
                        .append("'").append(entry.getValue()).append("'").append(AND);
            }
        if (sqlQuery.toString().endsWith(AND))
            sqlQuery.delete(sqlQuery.lastIndexOf(AND), sqlQuery.length());
        sqlQuery.append(")");
        return (WHERE + "()").equals(sqlQuery.toString()) ? "" : sqlQuery.toString();
    }

    protected List<Object> executeQuery(Class<T> buildClass, String sql) throws SQLException {
        List<Object> resultList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            if (buildClass != null) {
                ResultSet resultSet = statement.executeQuery(sql);
                BuilderProvider builderProvider = BuilderProvider.getInstance();
                Builder<?> builder = builderProvider.getBuilder(buildClass);
                while (resultSet.next()) {
                    resultList.add(builder.build(resultSet));
                }
            }
        } finally {
            if (connection != null)
                connectionPool.releaseConnection(connection);
        }
        return resultList;
    }

    private String makeInsertStatement(Map<String, Object> parameters) {
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        if (parameters != null && parameters.size() > 0) {
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                String column = entry.getKey();
                columns.append(column).append(", ");
                values.append("?, ");
            }
            columns.delete(columns.lastIndexOf(","), columns.length());
            values.delete(values.lastIndexOf(","), values.length());
        }
        columns.append(")");
        values.append(")");
        return INSERT_INTO + tableName + columns + VALUES + values;
    }

    private void prepare(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
        for (int i = 0; i < parameters.size(); i++) {
            if (parameters.get(i) == null)
                preparedStatement.setNull(i + 1, Types.NULL);
            else
                preparedStatement.setObject(i + 1, parameters.get(i));
        }
    }
}