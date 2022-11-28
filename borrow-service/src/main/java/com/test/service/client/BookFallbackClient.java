package com.test.service.client;

import com.test.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookFallbackClient implements BookClient {


    @Override
    public Book findBookById(int bid) {
        Book book = new Book();
        book.setTitle("尼古丁真");
        book.setDesc("我测你们吗");
        return book;
    }
}
