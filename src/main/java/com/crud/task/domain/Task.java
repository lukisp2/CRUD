package com.crud.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Task {
    private Long id;
    private String title;
    private String content;
}
