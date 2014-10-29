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
    private final String host;
    private final String port;
    private final String dbNavn;
    private final String user;
    private final String pass;
    private final String db;

    /**
     * Constructor, creates a new object of the class.
     */
    private DBConnection() throws ClassNotFoundException, SQLException {
        this.host = "localhost";
        this.port = "3306";
        this.dbNavn = "annetteprojekt";
        this.user = "root";
        this.pass = "root";
        this.db = "jdbc:mysql://" + host + ":" + port + "/" + dbNavn;
        isConnected = false;
        connection();
    }

    /**
     * Makes a connection to the specified database.
     *
     * @return a boolean based on connection - true if connected, false if not.
     */
    private boolean connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = (Connection) DriverManager.getConnection(db, user, pass);
        state = (Statement) connect.createStatement();
        isConnected = true;
        return isConnected;
    }

    /**
     * If an instance of this class returns null, it creates a new one and
     * return that.
     *
     * @return an instance of this class
     */
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
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
    public void execute(String sql) throws SQLException {
        state.execute(sql);
    }

    public ResultSet getResult(String sql) throws SQLException {
        ResultSet rs = state.executeQuery(sql);
        return rs;
    }

    public void close() throws SQLException {
        connect.close();
    }
}
