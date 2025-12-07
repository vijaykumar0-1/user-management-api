package com.usermanagementapi.service;

import com.usermanagementapi.dto.UserDto;
import com.usermanagementapi.model.User;

public interface UserService {
    String createUser(User user);
    String userLogin(UserDto user);
}
