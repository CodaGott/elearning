package io.elearning.web.service;

import io.elearning.data.dto.UserDto;
import io.elearning.data.models.Role;
import io.elearning.data.models.User;
import io.elearning.data.repository.UserRepository;
import io.elearning.exceptions.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    private ModelMapper modelMapper;

    public UserServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public User createUser(UserDto userDto) throws UserException {

        User user = new User();

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new UserException("User with email already exists");
        }
        modelMapper.map(userDto, user);
        user.setRole(Role.STUDENT);
        return userRepository.save(user);
    }

    @Override
    public User createAdmin(UserDto userDto) throws UserException {

        User user = new User();

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new UserException("User with email already exist");
        }
        modelMapper.map(userDto, user);
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    @Override
    public User createTeacher(UserDto userDto) throws UserException {
        User user = new User();

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new UserException("User with email already exists");
        }
        modelMapper.map(userDto, user);
        user.setRole(Role.TEACHER);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUserInfo(UserDto userDto, Long userId) throws UserException {
//        Course course = new Course();
        User userToUpdate = userRepository.findById(userId).orElseThrow(() ->
                new UserException("The user you want to update does not exist"));
        modelMapper.map(userDto, userToUpdate);
//        userToUpdate.addCourse(course);
        return userRepository.save(userToUpdate);
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserException("User with email not found"));
    }

    @Override
    public User getUserByUsername(String username) throws UserException {
        return userRepository.findByUserName(username).orElseThrow(
                () -> new UserException("User with " + username + " username not found"));
    }

    @Override
    public User getUserById(Long userId) throws UserException {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserException("User not found with id: " +userId));
    }

    @Override
    public void deleteUser(Long userId) throws UserException {
        User userToDelete = userRepository.findById(userId).orElseThrow(() ->
                new UserException("User with the id not found"));

        userRepository.delete(userToDelete);
    }
}
