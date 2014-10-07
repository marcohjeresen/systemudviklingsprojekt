package handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mark Hjeresen
 */
public class DBConnection {

    private Connection connect;
    private Statement state;
    private boolean isConnected;
    private static DBConnection connection;
    private String host;
    private String port;
    private String dbNavn;
    private String user;
    private String pass;
    private String db;

    /**
     * Constructor, creates a new object of the class.
     * 
     */
    public DBConnection() {

        this.dbNavn = "annetteprojekt";
        this.host = "localhost";
        this.port = "3306";
        this.user = "root";
        this.pass = "root";
        isConnected = false;
        connection();
    }

    /**
     * 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void connect() throws ClassNotFoundException, SQLException {
        isConnected = false;
        connection();
  
    }

    /**
     * Makes a connection to the specified database.
     *
     * @return a boolean based on connection - true if connected, false if not.
     */
    private boolean connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(db, user, pass);
            state = (Statement) connect.createStatement();
            isConnected = true;

        } catch (ClassNotFoundException ex) {
            System.out.println("db.DBConnection - connection(): Database Not Found!!" + ex.getLocalizedMessage());
        } catch (SQLException ex) {
            System.out.println("db.DBConnection - connection(): Connect Failed:" + ex.getLocalizedMessage());
        }
        return isConnected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    /**
     * If an instance of this class returns null, it creates a new one and
     * return that.
     *
     * @return an instance of this class
     */
    public static DBConnection getInstance() {
        if (connection == null) {
            connection = new DBConnection();
        }
        return connection;
    }

    /**
     *
     * @param sql a String containing the sql command
     * @throws SQLException
     */
    public void execute(String sql){
        try {
            state.execute(sql);
        } catch (SQLException ex) {
            System.out.println("db.DBConnection - DBConnection - execute(): Connect Failed:" + ex.getLocalizedMessage());
        }
    }

    public ResultSet getResult(String sql) {
        ResultSet rs = null;
        try {
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("db.DBConnection - DBConnection - getResult(): Connect Failed:" + ex.getLocalizedMessage());
        }
        return rs;
    }

    public void close()  {
        try {
            connect.close();
        } catch (SQLException ex) {
            System.out.println("db.DBConnection - DBConnection - close(): Connect Failed:" + ex.getLocalizedMessage());
        }
    }
}
