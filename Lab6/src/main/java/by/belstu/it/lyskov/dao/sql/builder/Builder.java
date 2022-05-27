package by.belstu.it.lyskov.dao.sql.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<T> {

    T build(ResultSet resultSet) throws SQLException;
}
