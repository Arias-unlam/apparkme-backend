package com.macons.apparkme.service;

import com.macons.apparkme.dto.UserRequest;
import com.macons.apparkme.entities.User;
public interface UserService {
    public User createUser(UserRequest userRequest);

    public User findUserByUsernameAndPassword( String username, String pwd);
}
