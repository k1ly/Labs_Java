package by.belstu.it.lyskov.dao.sql.dbtable;

public enum ItemTable {
    ID("id"),
    NAME("name"),
    PRICE("price"),
    OWNER_ID("owner_id");

    private final String columnName;

    ItemTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
