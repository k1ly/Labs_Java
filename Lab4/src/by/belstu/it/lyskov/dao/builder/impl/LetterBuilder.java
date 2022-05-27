package by.belstu.it.lyskov.dao.builder.impl;

import by.belstu.it.lyskov.bean.Letter;
import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.dao.builder.Builder;
import by.belstu.it.lyskov.dao.dbtable.LetterTable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LetterBuilder implements Builder<Letter> {

    @Override
    public Letter build(ResultSet resultSet) throws SQLException {
        Letter letter = new Letter();
        letter.setId(resultSet.getInt(LetterTable.ID.getColumnName()));
        letter.setSender(new User(resultSet.getInt(LetterTable.SENDER_ID.getColumnName())));
        letter.setReceiver(new User(resultSet.getInt(LetterTable.RECEIVER_ID.getColumnName())));
        letter.setText(resultSet.getString(LetterTable.TEXT.getColumnName()));
        letter.setTheme(resultSet.getString(LetterTable.THEME.getColumnName()));
        letter.setSendDate(resultSet.getTimestamp(LetterTable.SEND_DATE.getColumnName()));
        return letter;
    }
}
