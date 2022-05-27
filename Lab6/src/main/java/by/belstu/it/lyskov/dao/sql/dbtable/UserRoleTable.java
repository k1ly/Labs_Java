package by.belstu.it.lyskov.dao.sql.dbtable;

public enum UserRoleTable {
    ID("id"),
    ROLE_NAME("role_name");

    private final String columnName;

    UserRoleTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
