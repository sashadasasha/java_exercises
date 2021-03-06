import java.sql.*;

public class JDBCExercises {
    public static void main(String[] args)  {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=Europe/Moscow";
        String user = "sasha";
        String pass = "1234qwerty";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT course_name AS \"Название курса\", " +
                    "(count(course_name)/(max(month(subscription_date)) - min(month(subscription_date)) + 1)) AS \"Среднее количество продаж\" " +
                    "FROM skillbox.purchaselist GROUP BY course_name;")) {

            ResultSetMetaData rsmd = rs.getMetaData();
            String name = rsmd.getColumnName(1);
            String average = rsmd.getColumnName(2);
            System.out.println(name + " " + average);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
