package solutions.egen.kiran.hazelcastexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.egen.kiran.hazelcastexample.model.User;
import solutions.egen.kiran.hazelcastexample.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/onboarding")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/users/name/{name}")
    @Cacheable(value="usersCache", key="#name")
    public User findByName(@PathVariable final String name) {
        return userService.findByName(name);
    }

    @PostMapping(value = "/users")
    public ResponseEntity load(@RequestBody final User users) {
        userService.save(users);
        //return userService.findByName(users.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User product) {
        if (!userService.findById(id).isPresent()) {
            logger.error("Id " + id + " does not exist");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userService.save(product));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            logger.error("Id " + id + " does not exists");
            ResponseEntity.badRequest().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
