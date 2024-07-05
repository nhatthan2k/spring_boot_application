package com.appspringboot.dto.request;

import com.appspringboot.model.enumPackage.Gender;
import com.appspringboot.model.enumPackage.Role;
import com.appspringboot.model.enumPackage.UserStatus;
import com.appspringboot.validation.EnumPattern;
import com.appspringboot.validation.EnumValue;
import com.appspringboot.validation.GenderSubset;
import com.appspringboot.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.appspringboot.model.enumPackage.Gender.FEMALE;
import static com.appspringboot.model.enumPackage.Gender.MALE;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO implements Serializable {
    @NotBlank(message = "firstName must be not blank") // Khong cho phep gia tri blank
    private String firstName;

    @NotNull(message = "lastName must be not null") // Khong cho phep gia tri null
    private String lastName;

    @Email(message = "email invalid format") // Chi chap nhan nhung gia tri dung dinh dang email
    private String email;

    @EnumPattern(name = "status", regex = "ACTIVE|INACTIVE|NONE")
    private UserStatus status;

    @GenderSubset(anyOf = {MALE, FEMALE})
    private Gender gender;

    @EnumValue(name = "role", enumClass = Role.class)
    private String role;

    // @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    @PhoneNumber(message = "phone invalid format")
    private String phone;
}
