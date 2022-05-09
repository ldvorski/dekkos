package hr.mc2.dekkos.web.controller;

import hr.mc2.dekkos.model.User;
import hr.mc2.dekkos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usercontroller/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(path = "users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User newUser) {
        User user = userService.add(newUser);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }
}

