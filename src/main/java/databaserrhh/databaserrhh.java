package databaserrhh;


import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class databaserrhh {
    public static Connection getConnection() throws Exception {
        Context initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/MySQLResource");
        return ds.getConnection();
    }
}
