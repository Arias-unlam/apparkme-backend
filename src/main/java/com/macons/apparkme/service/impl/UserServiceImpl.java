package com.macons.apparkme.service.impl;

import com.macons.apparkme.dto.UserDTO;
import com.macons.apparkme.dto.UserRequest;
import com.macons.apparkme.entities.User;
import com.macons.apparkme.mapper.UserMapper;
import com.macons.apparkme.repository.UserRepository;
import com.macons.apparkme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    private UserServiceImpl (UserRepository userRepository, UserMapper  userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        User user = userMapper.UserRequestToUser(userRequest);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String pwd) {
        //User user = User.builder().username(username).password(pwd).build();
        User userFound = userRepository.findByUsernameAndPassword(username, pwd);
        return userFound;
    }


}
