package com.test.service.client;

import com.test.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookClientImpl implements BookClient{
    @Override
    public Book findBookById(int bid) {
        Book book = new Book();
        book.setTitle("书籍替代方案");
        return book;
    }
}
