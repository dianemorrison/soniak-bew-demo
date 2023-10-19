package org.example.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectDao {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    public boolean projectExists(int projectId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT project_id"  +
                " FROM `Project` where project_id=" + projectId);

        if(rs.next())
        {
            return true;
        }
        return false;
    }
}
