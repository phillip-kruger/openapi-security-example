package com.github.phillipkruger.company.security;

import io.agroal.api.AgroalDataSource;
import io.quarkus.runtime.StartupEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class Startup {
    
    private static final Logger LOG = Logger.getLogger(Startup.class.getName());
    
    @Inject
    AgroalDataSource defaultDataSource;
    
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        try(Connection connection = defaultDataSource.getConnection()){
            Statement stmt = connection.createStatement();  
            stmt.execute("CREATE TABLE test_user (\n" +
                                                "  id INT,\n" +
                                                "  username VARCHAR(255),\n" +
                                                "  password VARCHAR(255),\n" +
                                                "  role VARCHAR(255)\n" +
                                                ");");  
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        try(Connection connection = defaultDataSource.getConnection()){
            Statement stmt = connection.createStatement();  
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'dilbert', 'dilbert', 'employee');");
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'wally', 'wally', 'employee');");
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'alice', 'alice', 'employee');");
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'dogbert', 'dogbert', 'employee');");
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'catbert', 'catbert', 'employee');");
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'asok', 'asok', 'employee');");
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'ted', 'ted', 'employee');");
            stmt.execute("INSERT INTO test_user (id, username, password, role) VALUES (1, 'boss', 'boss', 'boss');");
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
