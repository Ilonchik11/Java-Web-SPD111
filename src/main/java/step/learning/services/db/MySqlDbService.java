package step.learning.services.db;

import com.google.inject.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;

@Singleton
public class MySqlDbService implements DbService {
    private Connection connection;
    @Override
    public Connection getConnection() {
        if(connection == null) {
            //  first time - connect
            String connectionString = "jdbc:mysql://localhost:3307/java_spd_111" +
                    "?useUnicode=true&characterEncoding=UTF-8";
            String dbUser = "spd_111";
            String dbPassword = "pass_111";
            try {
                // ask for driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        connectionString, dbUser, dbPassword
                );
            }
            catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return connection;
    }
}
