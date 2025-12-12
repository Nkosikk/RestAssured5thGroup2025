package utils;



import java.sql.*;

public class DatabaseConnection {

    public static String getEmail;
    public static String getPassword;


    public static void dbConection() throws SQLException {
        String dbUrl = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_teaching";
        String dbUsername = "ndosian6b8b7_teaching";
        String dbPassword = "^{SF0a=#~[~p)@l1";

        try (Connection connect = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
             Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM login_user WHERE id = 1")){

             while (resultSet.next()) {
                 getEmail = resultSet.getString("email");
                 getPassword = resultSet.getString("password");
                 System.out.println("Email: " + getEmail + ", Password: " + getPassword);
             }

        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}
