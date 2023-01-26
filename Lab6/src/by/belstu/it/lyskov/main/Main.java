package by.belstu.it.lyskov.main;

import by.belstu.it.lyskov.bean.User;
import by.belstu.it.lyskov.bean.Letter;
import by.belstu.it.lyskov.connection.ConnectionPool;
import by.belstu.it.lyskov.dao.UserDAO;
import by.belstu.it.lyskov.dao.LetterDAO;
import by.belstu.it.lyskov.dao.dbtable.LetterTable;
import by.belstu.it.lyskov.dao.dbtable.UserTable;
import by.belstu.it.lyskov.dao.exception.DAOException;
import by.belstu.it.lyskov.dao.parameter.ParameterMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    private static void init(LetterDAO letterDAO, UserDAO userDAO) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            User user1 = new User("John", Timestamp.from(formatter.parse("04/22/1993").toInstant()));
            User user2 = new User("Nick", Timestamp.from(formatter.parse("08/06/2002").toInstant()));
            User user3 = new User("Alice", Timestamp.from(formatter.parse("03/10/1985").toInstant()));
            userDAO.add(user1);
            userDAO.add(user2);
            userDAO.add(user3);
            Letter[] letters = {
                    new Letter(user1, user2, "Happy birthday", "Birthday",
                            Timestamp.from(formatter.parse("04/09/2019").toInstant())),
                    new Letter(user2, user1, "This is ad", "Advertising",
                            Timestamp.from(formatter.parse("07/08/2007").toInstant())),
                    new Letter(user2, user3, "Merry christmas", "Christmas",
                            Timestamp.from(formatter.parse("12/24/2016").toInstant())),
                    new Letter(user3, user2, "Love you", "Love",
                            Timestamp.from(formatter.parse("01/22/2021").toInstant())),
                    new Letter(user1, user3, "Stocks", "Business",
                            Timestamp.from(formatter.parse("10/15/2020").toInstant()))
            };
            for (Letter letter : letters) {
                letterDAO.add(letter);
            }
        } catch (ParseException | DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            ConnectionPool.getInstance();
            LetterDAO letterDAO = new LetterDAO();
            UserDAO userDAO = new UserDAO();

            init(letterDAO, userDAO);

            for (var user : userDAO.findAll()) {
                System.out.println(user);
                Map<String, Object> letterSenderId = new LinkedHashMap<>();
                letterSenderId.put(LetterTable.SENDER_ID.getColumnName(), user.getId());
                System.out.println("Отправлено писем: " + letterDAO.find(ParameterMap.of(letterSenderId)).size());
                Map<String, Object> letterReceiverId = new LinkedHashMap<>();
                letterReceiverId.put(LetterTable.RECEIVER_ID.getColumnName(), user.getId());
                System.out.println("Получено писем: " + letterDAO.find(ParameterMap.of(letterReceiverId)).size());
            }

            List<Letter> sortedLetters = letterDAO.browseSorted(LetterTable.SENDER_ID.getColumnName(), true);
            Map<User, Integer> senderTextLengths = new LinkedHashMap<>();
            for (Letter letter : sortedLetters) {
                senderTextLengths.put(letter.getSender(), letter.getText().length() +
                        (senderTextLengths.getOrDefault(letter.getSender(), 0)));
            }
            var minSenderTextLength = senderTextLengths.entrySet().stream()
                    .min(Comparator.comparingInt(Map.Entry::getValue));
            if (minSenderTextLength.isPresent()) {
                Optional<User> sender = userDAO.findUserById(minSenderTextLength.get().getKey().getId());
                sender.ifPresent(user -> System.out.println("Пользователь с наименьшей длиной отправленных писем: " + user));
            }

            String theme = "Christmas";
            Map<String, Object> letterTheme = new LinkedHashMap<>();
            letterTheme.put(LetterTable.THEME.getColumnName(), theme);
            List<Letter> themedLetters = letterDAO.find(ParameterMap.of(letterTheme));
            if (themedLetters.size() > 0) {
                System.out.println("Ползователи, получившие письмо с темой " + theme + ": ");
                for (Letter letter : themedLetters) {
                    Optional<User> user = userDAO.findUserById(letter.getReceiver().getId());
                    user.ifPresent(System.out::println);
                }
            }

            if (themedLetters.size() > 0) {
                System.out.println("Ползователи, не получившие письмо с темой " + theme + ": ");
                List<User> usersWithoutThemedLetters = userDAO.findAll();
                for (Letter letter : themedLetters) {
                    usersWithoutThemedLetters = usersWithoutThemedLetters.stream().filter(user
                            -> !user.getId().equals(letter.getReceiver().getId())).toList();
                }
                for (User user : usersWithoutThemedLetters) {
                    System.out.println(user);
                }
            }

            String newsletterSenderName = "John";
            String newsletterTheme = "Business";
            Map<String, Object> userName = new LinkedHashMap<>();
            userName.put(UserTable.NAME.getColumnName(), newsletterSenderName);
            List<User> sender = userDAO.find(ParameterMap.of(userName));
            User letterSender = sender.get(0);
            Map<String, Object> letterThemeAndSender = new LinkedHashMap<>();
            letterThemeAndSender.put(LetterTable.SENDER_ID.getColumnName(), letterSender.getId());
            letterThemeAndSender.put(LetterTable.THEME.getColumnName(), newsletterTheme);
            Letter letter = letterDAO.find(ParameterMap.of(letterThemeAndSender)).get(0);
            letterDAO.sendLetter(letter);
            System.out.println("Письмо с темой " + letter.getTheme() + " отправлно всем адресатам");
        } catch (DAOException e) {
            logger.error(e);
        } finally {
            ConnectionPool.getInstance().destroy();
        }
    }
}