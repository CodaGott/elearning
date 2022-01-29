package io.elearning.web.service;

import io.elearning.data.dto.UserDto;
import io.elearning.data.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Override
    public User createUser(UserDto userDto) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User updateUserInfo(UserDto userDto, Long userId) {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
