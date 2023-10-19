package org.example.api;

import org.example.cli.DeliveryEmployee;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.client.DeliveryEmployeeDoesNotExistException;
import org.example.client.FailedToDeleteEmployeeException;
import org.example.client.ProjectException;
import org.example.core.DeliveryEmployeeValidator;
import org.example.db.DeliveryEmployeeDao;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {
    DeliveryEmployeeDao deliveryEmployeeDao = new DeliveryEmployeeDao();
    DeliveryEmployeeValidator deliveryEmployeeValidator = new DeliveryEmployeeValidator();

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        return deliveryEmployeeDao.getAllDeliveryEmployees();
    }
    public int createDeliveryEmployee(DeliveryEmployeeRequest employee) throws ProjectException, SQLException {
            String validation = deliveryEmployeeValidator.isValid(employee);
            if(validation != null){
                throw new ProjectException();
            }
            int id = deliveryEmployeeDao.createDeliveryEmployee(employee);

            if(id == -1){
                throw new ProjectException();
            }
            return id;

    }

    public DeliveryEmployee getDeliveryEmployeeById (int id) throws SQLException, DeliveryEmployeeDoesNotExistException {
            DeliveryEmployee deliveryEmployee = deliveryEmployeeDao.getDeliveryEmployeeById(id);

                if(deliveryEmployee == null) {
                    throw new DeliveryEmployeeDoesNotExistException();
            }

            return deliveryEmployee;

    }

    public void deleteDeliveryEmployee(int id) throws FailedToDeleteEmployeeException, DeliveryEmployeeDoesNotExistException {
        try {
            DeliveryEmployee deliveryEmployeeToDelete = deliveryEmployeeDao.getDeliveryEmployeeById(id);

               if (deliveryEmployeeToDelete == null){
                   throw new DeliveryEmployeeDoesNotExistException();
               }
                deliveryEmployeeDao.deleteDeliveryEmployee(id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToDeleteEmployeeException();
        }
    }

}
