package com.ashu.book_store.service.Impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ashu.book_store.dto.BookDetailsResponse;
import com.ashu.book_store.dto.HomePageResponse;
import com.ashu.book_store.model.BookStore;
import com.ashu.book_store.service.BookStoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class BookStoreServiceImpl1 implements BookStoreService {
    private final JdbcClient jdbcClient;
    @Override
    public void createBook(BookStore bookStore, String filePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBook'");
    }

    @Override
    public String findImageById(int id) {
        String sql = "SELECT image FROM book_store WHERE book_id = ?";
       RowMapper<String> rowMapper = (resultSet,rn)-> resultSet.getString("image");
       return jdbcClient.sql(sql).param("id",id).query(rowMapper).optional().orElseThrow();
    }

    @Override
    public List<HomePageResponse> getBooksNameandId() {
       String sql = "SELECT book_id , book_name FROM book_store";
       return jdbcClient.sql(sql).query(HomePageResponse.class).list();
    }

    @Override
    public BookDetailsResponse getBook(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBook'");
    }

    @Override
    public void updateBook(int bookId, BookStore bookStore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBook'");
    }

    @Override
    public void updateImage(int bookId, MultipartFile bookImage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateImage'");
    }

    @Override
    public void deleteBookById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBookById'");
    }
    
}
