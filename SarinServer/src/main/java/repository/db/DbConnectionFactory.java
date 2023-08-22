
package repository.db;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

import form.DBConfigModel;

/**
 *
 * @author Sara
 */
public class DbConnectionFactory {

    private Connection connection;
    private static DbConnectionFactory instance;

    private DbConnectionFactory() {
    }

    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
        	String txtJson = new String(Files.readAllBytes(Paths.get("dbconfigJson.txt")));
            ObjectMapper objectMapper = new ObjectMapper();
            DBConfigModel dbConfigModel = objectMapper.readValue(txtJson, DBConfigModel.class);
            
            connection = DriverManager.getConnection(dbConfigModel.getUrl(), dbConfigModel.getUsername(), dbConfigModel.getPassword());
            System.out.println("Uspostavljena konekcija");
            connection.setAutoCommit(false);
        }
        return connection;
    }
    public void disconnect() throws SQLException
    {
        if(this.connection != null) {
            if(!this.connection.isClosed()) {
                this.connection.close();
            }
            
            this.connection = null;
        }
    }
}

