package com.appspringboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseSuccess extends ResponseEntity<ResponseSuccess.payload> {

    public ResponseSuccess(HttpStatusCode status, String message) {
        super(new payload(status.value(), message), HttpStatus.OK);
    }

    public ResponseSuccess(HttpStatusCode status, String message, Object data) {
        super(new payload(status.value(), message, data), HttpStatus.OK);
    }

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class payload {
        private final int status;
        private final String message;
        private Object data;
    }
}
