package com.crud.task.facade;

import com.crud.task.config.AdminConfig;
import com.crud.task.domain.*;
import com.crud.task.mapper.TaskMapper;
import com.crud.task.mapper.TrelloMapper;
import com.crud.task.service.TrelloService;
import com.crud.task.trello.client.TrelloClient;
import com.crud.task.trello.config.TrelloConfig;
import com.crud.task.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TrelloTests {

    @Autowired
    TrelloConfig trelloConfig;

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    TrelloValidator trelloValidator;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TrelloMapper trelloMapper;

    @Autowired
    TrelloService trelloService;

    @Autowired
    TrelloClient trelloClient;

    @Test
    void mapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        List<TrelloListDto> list = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto();
        trelloBoardDto1.setName("name");
        trelloBoardDto1.setId("id");
        trelloBoardDto1.setLists(list);
        trelloBoardDto.add(0, trelloBoardDto1);
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
        TrelloCardDto trelloCardDto = new TrelloCardDto(null, null, null, null);
        trelloCardDto.setName("card_name");
        trelloCardDto.setDescription("card_description");
        trelloCardDto.setPos("pos_card");
        trelloCardDto.setListId("listId_card");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assertions.assertEquals("card_name", trelloCard.getName());
        Assertions.assertEquals("card_description", trelloCard.getDescription());
        Assertions.assertEquals("pos_card", trelloCard.getPos());
        Assertions.assertEquals("listId_card", trelloCard.getListId());
    }

    @Test
    void trelloValidatorBoardTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> lists = new ArrayList<>();
        trelloBoards.add(0, new TrelloBoard("1", "test", lists));
        //When
        List<TrelloBoard> validatedBoard = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assertions.assertEquals(0, validatedBoard.size());
    }

    @Test
    void trelloValidatorCardTest() {
        //Given
        TrelloCard trelloCard =
                new TrelloCard("test", "test", "1", "id");
        //When
        boolean isTested = trelloValidator.validateCard(trelloCard);
        //Then
        Assertions.assertEquals(true, isTested);
    }


    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(null, null, null);
        taskDto.setId(1L);
        taskDto.setTitle("title");
        taskDto.setContent("content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assertions.assertEquals(1L, task.getId());
        Assertions.assertEquals("title", task.getTitle());
        Assertions.assertEquals("content", task.getContent());
    }


    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assertions.assertEquals(1L, taskDto.getId());
        Assertions.assertEquals("title", taskDto.getTitle());
        Assertions.assertEquals("content", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        final List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "title", "content"));
        //When
        List<TaskDto> taksDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assertions.assertEquals(1, taksDtos.size());
    }

    @Test
    void trelloConfigTest() {
        //Given
        //When
        String token = trelloConfig.getTrelloToken();
        String appKey = trelloConfig.getTrelloAppKey();
        String trelloUser = trelloConfig.getTrelloUser();
        String endPoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(appKey);
        Assertions.assertNotNull(trelloUser);
        Assertions.assertNotNull(endPoint);
    }

    @Test
    void adminConfigTest() {
        //Given
        //When
        String mail = adminConfig.getAdminMail();
        //Then
        Assertions.assertNotNull(mail);
    }


}







