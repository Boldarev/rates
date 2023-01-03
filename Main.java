import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws ParseException {

        // get connection to the "Rates" SQLite Database
        String jdbcUrl = "jdbc:sqlite:C:\\sqlite\\ratesdb.db";

        try {
              //get average buying courses from the ReadFilebyLine class
              ReadFileLineByLine avBuyRate = new ReadFileLineByLine();
              double averBuyRate = avBuyRate.returnBuySellRate();

              //get the current date
              String lt = java.time.LocalDate.now().toString();

              // add single quotes around the date to get it inserted in the SQlite db
              lt = "'"+lt+"'";
              System.out.println(lt);


              Connection connection = DriverManager.getConnection(jdbcUrl);


              PreparedStatement ps = connection.prepareStatement("insert into rates (avgbuy, lastonedate) values("+averBuyRate+","+lt+")");
              ps.executeUpdate();







        } catch (
                SQLException e) {
            System.out.println("Error connecting to SQLite database");
            e.printStackTrace();
        }

    }
}
