package database;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Fare on 13.10.2016.
 */
public class DBConnectionPool {

    private Set<Connection> availableConnections;
    private Set<Connection> usedConnections;
    private String connectionUrl;
    private String dbUser;
    private String dbPassword;
    private static final Logger logger = Logger.getLogger(DBConnectionPool.class);

    public DBConnectionPool(String connectionUrl, String dbDriverName,
                            String userName, String password, int connectionsCount) {
        this.availableConnections = new HashSet<>();
        this.usedConnections = new HashSet<>();
        this.connectionUrl = connectionUrl;
        this.dbUser = userName;
        this.dbPassword = password;
        checkDriver(dbDriverName);
        for (int i = 0; i < connectionsCount; i++) {
            this.availableConnections.add(getConnection());

        }
    }

    private void checkDriver(String dbDriverName) {
        try {
            Class.forName(dbDriverName);
        } catch (ClassNotFoundException e) {
            logger.error("JDBC Driver not found!!! " + e);
            throw new IllegalStateException("JDBC Driver not found! Connection pool not created!");
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(this.connectionUrl, this.dbUser, this.dbPassword);
        } catch (SQLException e) {
            logger.error("Error creating connection!!!! " + e);
        }
        return connection;
    }

    public synchronized Connection retrieveConnection() {
        if (this.availableConnections.size() == 0) {
            Connection conn = getConnection();
            this.usedConnections.add(conn);
            return conn;
        } else {
            Connection conn = this.availableConnections.iterator().next();
            this.usedConnections.add(conn);
            this.availableConnections.remove(conn);
            return conn;
        }
    }

    public synchronized void putbackConnection(Connection connection) {
        if (connection != null) {
            if (this.usedConnections.contains(connection)) {
                this.usedConnections.remove(connection);
                this.availableConnections.add(connection);
            } else {
                throw new IllegalStateException("This connection is never used in connection Pool!!!");
            }
        }
    }

}
