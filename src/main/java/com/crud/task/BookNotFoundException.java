package com.crud.task;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND, reason
        = "Book with provided id not found")
public class BookNotFoundException extends RuntimeException {
}
