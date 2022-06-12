package com.crud.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
public class Mail {
    public final String mailTo;
    public final Optional<String> toCc;
    public final String subject;
    public final String message;
}
