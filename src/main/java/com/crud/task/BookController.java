package com.crud.task;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("books")
@lombok.extern.log4j.Log4j2
@lombok.AllArgsConstructor
public class BookController {

    private BooksService booksService;

    @org.springframework.web.bind.annotation.GetMapping("/{id}")
    public BookDto getById(@org.springframework.web.bind.annotation.PathVariable("id") long id){
        log.info("GetById with id {} called", id);

        Book book = booksService.findById(id);

        return BookDto.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }

}
