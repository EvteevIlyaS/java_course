import java.sql.*;

public class DBConnection {
    private static Connection connection;

    private static String dbName = "JavaMySQL";
    private static String dbUser = "root";
    private static String dbPass = "88508850";

    private static StringBuilder votersBuilder = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthday DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "UNIQUE KEY(name(50), birthday))");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() {
        String sql = "INSERT INTO voter_count(name, birthday, `count`) VALUES" +
                votersBuilder.toString() +
                "ON DUPLICATE KEY UPDATE `count` = `count` + 1";
        try {
            DBConnection.getConnection().createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void addVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        if (votersBuilder.length() != 0) {
            votersBuilder.append(",");
        }
//        try {
        votersBuilder.append(String.format("('%s', '%s', 1)", name, birthDay));
        if (votersBuilder.length() > 30000000) {
            executeMultiInsert();
            System.out.println(1);
        }
//        } catch (OutOfMemoryError er) {
//            executeMultiInsert();
//            votersBuilder.setLength(0);
//        }
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        String sql = "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        if (!rs.next()) {
            DBConnection.getConnection().createStatement()
                    .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
                            name + "', '" + birthDay + "', 1)");
        } else {
            Integer id = rs.getInt("id");
            DBConnection.getConnection().createStatement()
                    .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
        }
        rs.close();
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
