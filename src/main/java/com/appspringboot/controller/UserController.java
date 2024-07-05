package com.appspringboot.controller;

import com.appspringboot.configuration.Translator;
import com.appspringboot.dto.request.UserRequestDTO;
import com.appspringboot.dto.response.ResponseData;
import com.appspringboot.exception.ResourceNotFoundException;
import com.appspringboot.model.enumPackage.Gender;
import com.appspringboot.model.enumPackage.Role;
import com.appspringboot.model.enumPackage.UserStatus;
import com.appspringboot.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseData<Integer> addUser(@RequestBody @Valid UserRequestDTO user) {
        try {
            userService.addUser(user);
            return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1);
        } catch (ResourceNotFoundException e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "not found name");
        }
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable @Min(value = 1, message = "userId must be greater than 0") int userId, @RequestBody @Valid UserRequestDTO user) {
        System.out.println("Update user");
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully");
    }

    @PatchMapping("/{userId}")
    public ResponseData<?> changeUserStatus(@PathVariable int userId, @RequestParam boolean status) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable int userId){
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User Deleted");
    }

    @GetMapping("/{userId}")
    public ResponseData<List<UserRequestDTO>> getUser(@PathVariable int userId) {
        return new ResponseData<>(HttpStatus.OK.value(), "Get successfully", List.of(new UserRequestDTO("Tay", "Java", "admin@tayjava.vn",UserStatus.ACTIVE, Gender.MALE, Role.ADMIN.toString() , "0123456789"),
                new UserRequestDTO("Tay", "Java", "admin@tayjava.vn", UserStatus.ACTIVE, Gender.MALE, Role.ADMIN.toString() , "0123456789")));
    }
}
