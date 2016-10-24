package database;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.function.Function;

/**
 * Created by Fare on 19.07.2016.
 */
public class DataBaseProvider {

    private final static Logger logger = Logger.getLogger(DataBaseProvider.class);
    private DBConnectionPool dbConnectionPool;

    public DataBaseProvider (DBConnectionPool dbConnectionPool){
        this.dbConnectionPool = dbConnectionPool;
    }

    public boolean executeQuery(String query) {
        Statement statement;
        Connection connection = dbConnectionPool.retrieveConnection();
        try {
            statement = connection.createStatement();
            statement.execute(query);
            dbConnectionPool.putbackConnection(connection);
            //statement.close();
            //this.connection.close();
        } catch (SQLException e) {
            logger.error("Error query execution", e);
            return false;
        }
        return true;
    }

    public boolean checkTable(String tableName) {
        try {
            Connection connection = dbConnectionPool.retrieveConnection();
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, "public", tableName.toLowerCase(), null);
            if (tables.next()) {
                // Table exists
                dbConnectionPool.putbackConnection(connection);
                return true;
            } else {
                // Table not exists
                dbConnectionPool.putbackConnection(connection);
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error checking table!!!!", e);
        }
        return false;
    }

    public void executeQueryWithNoResult(String query, Function<PreparedStatement, PreparedStatement> function) {
        Connection connection = dbConnectionPool.retrieveConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            function.apply(preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            dbConnectionPool.putbackConnection(connection);
        }
        catch (SQLException e){
            logger.error("Error query with no result execution", e);
        }
    }

    public <T> T executeQueryWithResult (String query, Function<PreparedStatement,
            PreparedStatement> stPrepFunction, Function<ResultSet, T> objPrepFunction){
        Connection connection = dbConnectionPool.retrieveConnection();
        PreparedStatement preparedStatement;
        T t = null;
        try{
            preparedStatement = connection.prepareStatement(query);
            if(stPrepFunction.apply(preparedStatement).execute()){
                ResultSet rs = preparedStatement.getResultSet();
                t = objPrepFunction.apply(rs);
                rs.close();
            }
            preparedStatement.close();
            dbConnectionPool.putbackConnection(connection);
        }
        catch (SQLException e){
            logger.error("Error query with result execution", e);
        }
        return t;
    }

}
