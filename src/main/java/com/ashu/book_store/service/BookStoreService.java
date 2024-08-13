package com.ashu.book_store.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ashu.book_store.dto.BookDetailsResponse;
import com.ashu.book_store.dto.HomePageResponse;
import com.ashu.book_store.model.BookStore;

public interface BookStoreService {
                /** Methods  */
    void createBook(BookStore bookStore, String filePath);

    String findImageById(int id);

    List<HomePageResponse> getBooksNameandId();

    BookDetailsResponse getBook(int id);

     void updateBook(int bookId, BookStore bookStore) ;

     void updateImage(int bookId, MultipartFile bookImage);

     void deleteBookById(int id);


    
}
