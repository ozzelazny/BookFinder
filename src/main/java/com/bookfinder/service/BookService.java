package com.bookfinder.service;

import com.bookfinder.entity.Book;

import java.util.List;

public interface BookService {

   List<Book> seach(String keyword, SearchType searchType, Integer page);

}
