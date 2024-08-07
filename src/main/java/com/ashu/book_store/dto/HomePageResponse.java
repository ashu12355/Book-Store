package com.ashu.book_store.dto;

/**
 * HomePageResponse
 */
public record HomePageResponse(
        int bookId, // By Default it is private final
        String bookName) {
}