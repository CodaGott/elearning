package io.elearning.web.controller;

import io.elearning.data.dto.UserDto;
import io.elearning.exceptions.UserException;
import io.elearning.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> createUserAccount(@RequestBody UserDto userDto){
        try {
            userService.createUser(userDto);
            return new ResponseEntity<>("Account created successfully.", HttpStatus.CREATED);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("admin-signup")
    public ResponseEntity<?> createAdminAccount(@RequestBody UserDto userDto){
        try {
            userService.createAdmin(userDto);
            return new ResponseEntity<>("Admin Account created successfully", HttpStatus.CREATED);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("teacher-signup")
    public ResponseEntity<?> createTeacherAccount(@RequestBody UserDto userDto){
        try {
            userService.createTeacher(userDto);
            return new ResponseEntity<>("Teacher account created", HttpStatus.CREATED);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("allusers")
    public ResponseEntity<?> getAllUsers(){
        userService.getAllUsers();
        return new ResponseEntity<>("All users", HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){
        try {
            userService.getUserById(userId);
            return new ResponseEntity<>("User with id of "+ userId + "found", HttpStatus.FOUND);
        }catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto userDto, @PathVariable Long id){
        try {
            userService.updateUserInfo(userDto, id);
            return new ResponseEntity<>("User info updated successfully", HttpStatus.OK);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        try {
            userService.getUserByEmail(email);
            return new ResponseEntity<>("User found using the email provided", HttpStatus.FOUND);
        }
        catch (UserException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
