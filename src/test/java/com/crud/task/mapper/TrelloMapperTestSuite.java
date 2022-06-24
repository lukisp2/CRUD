package com.crud.task.mapper;


import com.crud.task.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void mapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        List<TrelloListDto> list = new ArrayList<>();
        trelloBoardDto.add(0, new TrelloBoardDto("id", "name", list));
        //When
        List<TrelloBoard> boardList = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        Assertions.assertEquals(1, boardList.size());
        Assertions.assertEquals("name", boardList.get(0).getName());
        Assertions.assertEquals("id", boardList.get(0).getId());
        Assertions.assertEquals(0, boardList.get(0).getLists().size());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(0, new TrelloList());
        trelloBoards.add(new TrelloBoard("id", "name", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assertions.assertEquals(1, trelloBoardDtos.size());
        Assertions.assertEquals("name", trelloBoardDtos.get(0).getName());
        Assertions.assertEquals("id", trelloBoardDtos.get(0).getId());
        Assertions.assertEquals(1, trelloBoardDtos.get(0).getLists().size());
    }

    @Test
    void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("id", "name", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        Assertions.assertEquals(1, trelloLists.size());
        Assertions.assertEquals("id", trelloLists.get(0).getId());
        Assertions.assertEquals("name", trelloLists.get(0).getName());
        Assertions.assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(0, new TrelloList("id", "name", false));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assertions.assertEquals(1, trelloListDtos.size());
        Assertions.assertEquals("id", trelloListDtos.get(0).getId());
        Assertions.assertEquals("name", trelloListDtos.get(0).getName());
        Assertions.assertFalse(trelloListDtos.get(0).isClosed());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assertions.assertEquals("name", trelloCardDto.getName());
        Assertions.assertEquals("description", trelloCardDto.getDescription());
        Assertions.assertEquals("pos", trelloCardDto.getPos());
        Assertions.assertEquals("listId", trelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card_name", "card_description", "pos_card", "listId_card");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assertions.assertEquals("card_name", trelloCard.getName());
        Assertions.assertEquals("card_description", trelloCard.getDescription());
        Assertions.assertEquals("pos_card", trelloCard.getPos());
        Assertions.assertEquals("listId_card", trelloCard.getListId());
    }
}