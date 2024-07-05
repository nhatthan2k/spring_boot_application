package com.appspringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseData<T> {
    private final int status;
    private final String message;
    private T data;
}
