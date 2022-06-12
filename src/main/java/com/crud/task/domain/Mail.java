package com.crud.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    public final String mailTo;
    public final String subject;
    public final String message;
}
