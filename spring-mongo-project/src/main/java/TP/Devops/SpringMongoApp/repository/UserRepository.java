package TP.Devops.SpringMongoApp.repository;
import TP.Devops.SpringMongoApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
