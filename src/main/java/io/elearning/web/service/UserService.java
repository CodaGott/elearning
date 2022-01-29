package io.elearning.web.service;

import io.elearning.data.dto.UserDto;
import io.elearning.data.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser (UserDto userDto);
    List<User> getAllUsers();
    User updateUserInfo(UserDto userDto, Long userId);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserById(Long userId);
    void deleteUser (Long userId);
}
