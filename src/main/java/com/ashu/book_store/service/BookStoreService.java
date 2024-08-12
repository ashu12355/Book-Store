package com.ashu.book_store.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ashu.book_store.dto.BookDetailsResponse;
import com.ashu.book_store.dto.HomePageResponse;
import com.ashu.book_store.model.BookFormat;
import com.ashu.book_store.model.BookStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookStoreService {
    private final JdbcTemplate jdbcTemplate;
    private final FileService fileService;

    public void createBook(BookStore bookStore, String filePath) {
        String sql = """
                INSERT INTO book_store (
                    book_name ,author_name ,total_page ,publication_date ,book_format ,category,availabilty,book_description,image) VALUES (?,?,?,?,?,?,?,?,?)
                    """;
        jdbcTemplate.update(sql,
                bookStore.getBookName(),
                bookStore.getAuthorName(),
                bookStore.getTotalPage(),
                bookStore.getPublicationDate(),
                bookStore.getBookFormat().name(),
                bookStore.getCategory(),
                listToCSV(bookStore.getAvailability()),
                bookStore.getDescription(),
               filePath);
    }
    public String findImageById(int id) {
       String sql = "SELECT image FROM book_store WHERE book_id = ?";
       RowMapper<String> rowMapper = (resultSet,rn)-> resultSet.getString("image");
       return jdbcTemplate.queryForObject(sql, rowMapper,id);
    }

    public List<HomePageResponse> getBooksNameandId() {
        String sql = "SELECT book_id , book_name FROM book_store";
        return jdbcTemplate.query(sql, homePageRowMapper());
    }

    public BookDetailsResponse getBook(int id) {
        String sql = "SELECT * FROM book_store WHERE book_id = ? ";
        return jdbcTemplate.queryForObject(sql, getBookDetailsRowMapper(), id);
    }
    public void deleteBookById(int id) {
        String sql = "DELETE FROM book_store WHERE book_id = ?";
        jdbcTemplate.update(sql,id);
     }

     public void updateBook(int bookId, BookStore bookStore) {
        String sql = """
                UPDATE book_store SET 
                    book_name = ?,
                    author_name = ?,
                    total_page = ?,
                    publication_date = ?,
                    book_format = ?,
                    category = ?,
                    availabilty = ?,
                    book_description = ?
                WHERE book_id = ?
                """;
        jdbcTemplate.update(sql,
                bookStore.getBookName(),
                bookStore.getAuthorName(),
                bookStore.getTotalPage(),
                bookStore.getPublicationDate(),
                bookStore.getBookFormat().name(),
                bookStore.getCategory(),
                listToCSV(bookStore.getAvailability()),
                bookStore.getDescription(),
                bookId);
    }
    

    private RowMapper<BookDetailsResponse> getBookDetailsRowMapper() {
        return (resultSet, rowNumber) -> {
            int bookId = resultSet.getInt("book_id");
            String bookName = resultSet.getString("book_name");
            String authorName = resultSet.getString("author_name");
            int totalPage = resultSet.getInt("total_page");
            String category = resultSet.getString("category");
            Date publicationDate = resultSet.getDate("publication_date");
            String bookFormat = resultSet.getString("book_format");
            String availability = resultSet.getString("availabilty");
            String description = resultSet.getString("book_description");

            return new BookDetailsResponse(bookId,
                    bookName,
                    authorName,
                    totalPage,
                    category,
                    sqlDateToLocalDate(publicationDate),
                    BookFormat.valueOf(bookFormat),
                    csvToList(availability),
                    description);
        };
    }

    private LocalDate sqlDateToLocalDate(Date date) {
        var formatter = new SimpleDateFormat("yyyy-MM-dd");
        return LocalDate.parse(formatter.format(date));
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

    private List<String> csvToList(String string) {
        return Arrays.stream(string.split(",")).toList();
    }
    public void updateImage(int bookId, MultipartFile bookImage) {
        if(!bookImage.isEmpty()) {
            try {
                String path = fileService.uploadImage(bookImage);
                String sql = "UPDATE book_store SET image = ? WHERE book_id = ?";
                jdbcTemplate.update(sql,path,bookId);
            } catch (Exception e) {
               throw new RuntimeException();
            }

        }
    }
    
   

   
   

    
}
