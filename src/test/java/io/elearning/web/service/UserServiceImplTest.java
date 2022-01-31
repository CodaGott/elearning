package io.elearning.web.service;

import io.elearning.data.dto.UserDto;
import io.elearning.data.models.Role;
import io.elearning.data.models.User;
import io.elearning.data.repository.CourseRepository;
import io.elearning.data.repository.ReviewRepository;
import io.elearning.data.repository.UserRepository;
import io.elearning.exceptions.UserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ReviewRepository reviewRepository;

    ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(modelMapper);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void userCanBeCreated() throws UserException {
        UserDto userDto = new UserDto();
        userDto.setUserName("username");
        userDto.setEmail("user@email.com");
        userDto.setRole(Role.STUDENT);
        userDto.setFullName("Users full name");

        userService.createUser(userDto);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    void adminUserCanBeCreated() throws UserException {
        UserDto userDto = new UserDto();

        userDto.setUserName("admin_username");
        userDto.setEmail("admin@email.com");
        userDto.setRole(Role.ADMIN);
        userDto.setFullName("Admin full name");

        userService.createAdmin(userDto);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository, times(1)).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    void teacherCanBeCreated() throws UserException {
        UserDto userDto = new UserDto();
        userDto.setUserName("teacher username");
        userDto.setPassword("password");
        userDto.setRole(Role.TEACHER);
        LocalDate birthday = LocalDate.ofYearDay(1990, 14);
        userDto.setFullName("Teacher full name");
        userDto.setDateOfBirth(birthday);

        userService.createTeacher(userDto);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(argumentCaptor.capture());
        User capturedUser = argumentCaptor.getValue();

        assertThat(capturedUser.getDateOfBirth()).isEqualTo(userDto.getDateOfBirth());
    }

    @Test
    void userInfoCanBeUpdated() throws UserException {
        User user = new User();
        Long userId = 4L;
        user.setUserName("Username");
        user.setEmail("usermail");
        user.setRole(Role.STUDENT);
        user.setPassword("password");
        user.setId(userId);
        userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setUserName("student username");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        userService.updateUserInfo(userDto, userId);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(2)).save(captor.capture());
        User updatedUser = captor.getValue();

        assertThat(user.getUserName()).isEqualTo(updatedUser.getUserName());
    }

    @Test
    void userCanBeFoundUsingId() throws UserException {
        User user1 = new User();
        User user2 = new User();
        user1.setId(1L);
        user2.setId(2L);

        user1.setUserName("user1 username");
        user2.setUserName("user2 username");

        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        assertThat(userService.getUserById(user1.getId())).isEqualTo(user1);
    }

    @Test
    void allUsersCanBeReturned(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setId(1L);
        user2.setId(2L);
        user1.setUserName("user1 username");
        user3.setId(3L);
        user1.setUserName("user3 username");
        user2.setUserName("user2 username");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        when(userRepository.findAll()).thenReturn(users);
        when(userService.getAllUsers()).thenReturn(users);
        assertEquals(3, userService.getAllUsers().size());
    }

    @Test
    void userCanBeDelete() throws UserException {
        User user = new User();
        Long userId = 4L;
        user.setUserName("Username");
        user.setEmail("usermail");
        user.setRole(Role.STUDENT);
        user.setPassword("password");
        user.setId(userId);
        userRepository.save(user);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.deleteUser(user.getId());
        verify(userRepository).delete(user);
    }
}