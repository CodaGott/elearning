package io.elearning.web.service;

import io.elearning.data.dto.UserDto;
import io.elearning.data.models.User;
import io.elearning.exceptions.UserException;

import java.util.List;

public interface UserService {
    User createUser (UserDto userDto) throws UserException;
    User createAdmin (UserDto userDto) throws UserException;
    User createTeacher (UserDto userDto) throws UserException;


    List<User> getAllUsers();
    User updateUserInfo(UserDto userDto, Long userId) throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserByUsername(String username) throws UserException;
    User getUserById(Long userId) throws UserException;
    void deleteUser (Long userId) throws UserException;
}
