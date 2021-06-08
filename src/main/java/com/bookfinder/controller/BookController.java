package com.bookfinder.controller;

import com.bookfinder.entity.Book;
import com.bookfinder.service.BookServiceImpl;
import com.bookfinder.service.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/books")
    @PreAuthorize("hasRole('USER')")
    public List<Book> findBooks(@RequestParam(required = false) String term,
                                @RequestParam(required = false) String type,
                                @RequestParam(required = false, defaultValue = "1") Integer page) {

        return bookService.seach(term,
                Optional.ofNullable(type)
                        .filter(str -> !str.isEmpty())
                        .map(String::toUpperCase)
                        .map(SearchType::valueOf)
                        .orElse(SearchType.ALL),
                page);
    }
}
