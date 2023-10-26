package org.example.db;

import org.example.cli.DeliveryEmployee;
import org.example.cli.DeliveryEmployeeProjectRequest;
import org.example.cli.DeliveryEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {

     DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT `delivery_employee_id`," +
                "`name`," +
                "`salary`," +
                "`bank_account_number`," +
                "`national_insurance_number`" +
                "FROM `DeliveryEmployee`;");

        List<DeliveryEmployee> emoloyeeList = new ArrayList<>();

        while (rs.next()) {
            DeliveryEmployee employee = new DeliveryEmployee(
                    rs.getInt("delivery_employee_id"),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account_number"),
                    rs.getString("national_insurance_number")
            );
            emoloyeeList.add(employee);
        }

        return emoloyeeList;

    }
    public int createDeliveryEmployee(DeliveryEmployeeRequest employee) throws SQLException{
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO `DeliveryEmployee` (`name`, `salary`, `bank_account_number`, `national_insurance_number`) VALUES (?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement. RETURN_GENERATED_KEYS);

        st.setString(1, employee.getName());
        st.setDouble(2, employee.getSalary());
        st.setString(3, employee.getBank_account_number());
        st.setString(4, employee.getNational_insurance_number());


        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest deliveryEmployee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE `DeliveryEmployee` SET `name` = ?, `salary` = ?, `bank_account_number` = ? WHERE `delivery_employee_id` = ?;";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, deliveryEmployee.getName());
        st.setDouble(2, deliveryEmployee.getSalary());
        st.setString(3, deliveryEmployee.getBank_account_number());
        st.setInt(4, id);

        st.executeUpdate();
    }
    public void assignDeliveryEmployeeToProject(List<DeliveryEmployeeProjectRequest> requests) throws SQLException{
        Connection c = databaseConnector.getConnection();

        c.setAutoCommit(false);

        String insertStatement = "INSERT INTO `DeliveryEmployeeProject` (`project_id`, `delivery_employee_id`, `is_Active`, `created_On`, `updated_On`) VALUES (?,?,?,?,?)";
        PreparedStatement st = c.prepareStatement(insertStatement, Statement. RETURN_GENERATED_KEYS);

        for(DeliveryEmployeeProjectRequest request : requests){
            st.setInt(1, request.getProject_id());
            st.setInt(2, request.getDelivery_employee_id());
            st.setBoolean(3, request.isIs_Active());
            st.setDate(4, request.getCreated_On());
            st.setDate(5, request.getUpdated_On());
            st.addBatch();
        }

        st.executeBatch();

        c.commit();

        c.close();
    }
    public boolean deliveryEmployeeExists(int employeeId) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("SELECT delivery_employee_id"  +
                " FROM `DeliveryEmployee` where delivery_employee_id=" + employeeId);

        if(rs.next())
        {
            return true;
        }
        return false;
    }

    public void deleteDeliveryEmployee(int id) throws SQLException {

        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE from DeliveryEmployee WHERE delivery_employee_id = ? ";

        PreparedStatement st = c.prepareStatement(deleteStatement);

        st.setInt(1, id);

        st.executeUpdate();
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        String selectStatement  = "SELECT delivery_employee_id, `name`, salary, bank_account_number, national_insurance_number FROM DeliveryEmployee WHERE delivery_employee_id = ?;";

        PreparedStatement st = c.prepareStatement(selectStatement);

        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            return new DeliveryEmployee(
                    rs.getInt("delivery_employee_id"),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    rs.getString("bank_account_number"),
                    rs.getString("national_insurance_number")
            );
        }
        return null;
    }
}





