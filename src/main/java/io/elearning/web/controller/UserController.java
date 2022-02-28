package io.elearning.web.controller;

import io.elearning.data.dto.UserDto;
import io.elearning.data.models.User;
import io.elearning.exceptions.UserException;
import io.elearning.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> createUserAccount(@RequestBody UserDto userDto){
        try {
            User newUser = userService.createUser(userDto);
            log.info("Student account created successfully for: "+ newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("admin-signup")
    public ResponseEntity<?> createAdminAccount(@RequestBody UserDto userDto){
        try {
            User adminUser = userService.createAdmin(userDto);
            log.info(" Admin Account created successfully");
            return new ResponseEntity<>(adminUser, HttpStatus.CREATED);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("teacher-signup")
    public ResponseEntity<?> createTeacherAccount(@RequestBody UserDto userDto){
        try {
            User teacher = userService.createTeacher(userDto);
            log.info("Teacher account created");
            return new ResponseEntity<>(teacher, HttpStatus.CREATED);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("users")
    public ResponseEntity<?> getAllUsers(){
        log.info("Getting Users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        try {
            log.info("User with id of "+ userId + "found");
            return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.FOUND);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto userDto, @PathVariable Long id){
        try {
            log.info("User info updated successfully");
            return new ResponseEntity<>(userService.updateUserInfo(userDto, id), HttpStatus.OK);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("user-by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try {
            User user = userService.getUserByEmail(email);
            log.info(user +" found using " + email + " provided");
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user_by_username/{username}")
    public ResponseEntity<?> getUserWithUsername(@PathVariable String username){
        try {
            User user = userService.getUserByUsername(username);
            log.info(user +" found using " + username + " provided");
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            log.info("User deleted successfully");
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
