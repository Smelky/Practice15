
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Logger logger = Logger.getLogger(Connector.class);

    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = Connector.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            properties.load(inputStream);
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (SQLException | IOException e) {
            logger.error("Exception: " + e);
            throw new DBRunTimeException(e.getMessage());
        }
        return connection;
    }
}
