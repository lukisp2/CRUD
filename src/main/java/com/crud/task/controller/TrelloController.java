package com.crud.task.controller;

import com.crud.task.domain.TrelloBoardDto;
import com.crud.task.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        List<TrelloBoardDto> filteredDto = trelloBoards
                .stream()
                .filter(t -> t.getName().contains("Kodilla"))
                .filter(dto -> !dto.getName().isBlank())
                .filter(dto -> !dto.getId().isBlank())
                .collect(Collectors.toList());
        System.out.println("Filtered Boards:");
        filteredDto.forEach(System.out::println);
    }

    ;
}
