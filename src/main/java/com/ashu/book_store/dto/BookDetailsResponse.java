package com.ashu.book_store.dto;

import java.time.LocalDate;
import java.util.List;

import com.ashu.book_store.model.BookFormat;

public record BookDetailsResponse(
        int bookId,
        String bookName,
        String authorName,
        int totalPage,
        String category,
        LocalDate publicationDate,
        BookFormat bookFormat,
        List<String> availability,
        String description) {
}
