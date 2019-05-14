package database;

import java.sql.*;

public class DBConnection {

    public Connection getConnection() {
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://mgstesting.heliohost.org:3306/mgomez_testing","mgomez","Nomeacuerd0!");

        }catch(Exception e){ System.out.println(e);}

        return con;
    }

    public void closeStament(Statement statement) throws SQLException {
        statement.close();
    }

    public void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }

    public void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
}
