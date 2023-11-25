package TP.Devops.SpringMongoApp.repository;
import TP.Devops.SpringMongoApp.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepo extends MongoRepository<Employee, String> {
    void deleteEmployeeById(String id);

    Optional<Employee> findEmployeeById(String id);
}
