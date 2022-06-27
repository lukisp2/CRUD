package com.crud.task;

@org.springframework.stereotype.Service
@lombok.AllArgsConstructor
public class BooksService {

    private BooksRepository booksRepository;

    public com.crud.task.Book findById(long id) {
        java.util.Optional<Book> bookOptional = booksRepository.findById(id);

        return bookOptional.orElseThrow(com.crud.task.BookNotFoundException::new);
    }
}
