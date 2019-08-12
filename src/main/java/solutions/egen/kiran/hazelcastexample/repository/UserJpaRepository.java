package solutions.egen.kiran.hazelcastexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import solutions.egen.kiran.hazelcastexample.model.User;

@Component
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}