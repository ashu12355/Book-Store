package com.ashu.book_store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

import com.ashu.book_store.model.BookFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsResponse {
    private int bookId;
    private String bookName;
    private String authorName;
    private int totalPage;
    private String category;
    private LocalDate publicationDate;
    private BookFormat bookFormat;
    private List<String> availability;
    private String description;
}