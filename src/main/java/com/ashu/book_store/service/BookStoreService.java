package com.ashu.book_store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ashu.book_store.dto.HomePageResponse;
import com.ashu.book_store.model.BookStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookStoreService {
    private final JdbcTemplate jdbcTemplate;

    public void createBook(BookStore bookStore) {
        String sql = """
                INSERT INTO book_store (
                    book_name ,author_name ,total_page ,publication_date ,book_format ,category,availabilty,book_description ) VALUES (?,?,?,?,?,?,?,?)
                    """;
        jdbcTemplate.update(sql,
                bookStore.getBookName(),
                bookStore.getAuthorName(),
                bookStore.getTotalPage(),
                bookStore.getPublicationDate(),
                bookStore.getBookFormat().name(),
                bookStore.getCategory(),
                listToCSV(bookStore.getAvailability()),
                bookStore.getDescription());
    }

    public List<HomePageResponse> getBooksNameandId() {
        String sql = "SELECT book_id , book_name FROM book_store";
        return jdbcTemplate.query(sql, homePageRowMapper());
    }

    private RowMapper<HomePageResponse> homePageRowMapper() {
        return (resultSet, rowNumber) -> {
            int bookId = resultSet.getInt("book_id");
            String bookName = resultSet.getString("book_name");
            return new HomePageResponse(bookId, bookName);
        };
    }

    private String listToCSV(List<String> list) {
        return list.stream()
                .collect(Collectors.joining(","));

    }
}
