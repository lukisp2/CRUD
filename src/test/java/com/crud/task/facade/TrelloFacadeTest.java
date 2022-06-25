package com.crud.task.facade;


import com.crud.task.domain.*;
import com.crud.task.mapper.TrelloMapper;
import com.crud.task.service.TrelloService;
import com.crud.task.trello.facade.TrelloFacade;
import com.crud.task.trello.validator.TrelloValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {


    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());
        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        // Then
        Assertions.assertThat(trelloBoardDtos).isNotNull();
        Assertions.assertThat(trelloBoardDtos.size()).isEqualTo(0);

    }

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists =
                List.of(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards =
                List.of(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards =
                List.of(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        Assertions.assertThat(trelloBoardDtos).isNotNull();
        Assertions.assertThat(trelloBoardDtos.size()).isEqualTo(1);

        trelloBoardDtos.forEach(trelloBoardDto -> {

            Assertions.assertThat(trelloBoardDto.getId()).isEqualTo("1");
            Assertions.assertThat(trelloBoardDto.getName()).isEqualTo("test");

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                Assertions.assertThat(trelloListDto.getId()).isEqualTo("1");
                Assertions.assertThat(trelloListDto.getName()).isEqualTo("test_list");
                Assertions.assertThat(trelloListDto.isClosed()).isFalse();
            });
        });
    }

    @Test
    void createCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "des", "1", "1");
        TrelloCard mappedCard = new TrelloCard("name", "des", "1", "1");
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto("id", "name", "https://test.pl"));
        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(mappedCard);
        when(trelloMapper.mapToCardDto(mappedCard)).thenReturn(trelloCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        createdTrelloCardDto.setId(null);
        createdTrelloCardDto.setName(null);
        createdTrelloCardDto.setShortUrl(null);
        createdTrelloCardDto = trelloFacade.createCard(trelloCardDto);
        //Then
        Assertions.assertThat(createdTrelloCardDto.getId()).isEqualTo("id");
        Assertions.assertThat(createdTrelloCardDto.getName()).isEqualTo("name");
        Assertions.assertThat(createdTrelloCardDto.getShortUrl()).isEqualTo("https://test.pl");
    }
}