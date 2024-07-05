package com.appspringboot.service.impl;

import com.appspringboot.dto.request.UserRequestDTO;
import com.appspringboot.exception.ResourceNotFoundException;
import com.appspringboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(UserRequestDTO user) {
        if (!user.getFirstName().equals("nhat")) {
            throw new ResourceNotFoundException("không tìm thấy nhat");
        }
    }
}
