package solutions.egen.kiran.hazelcastexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import solutions.egen.kiran.hazelcastexample.model.User;
import solutions.egen.kiran.hazelcastexample.repository.UserJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Cacheable(value="itemCache", key="#id")
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    @CachePut(value="usersCache")
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @CacheEvict(value="usersCache",key = "#id")
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Cacheable(value="usersCache", key="#name")
    public User findByName(String name) {
        return userJpaRepository.findByName(name);
    }

}
