package com.ashu.book_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.ashu.book_store.model.BookStore;
import com.ashu.book_store.service.BookStoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookStoreController {
    private final BookStoreService service;

    @GetMapping({ "/", "/home" })
    public String home() {
        return "home";
    }

    @GetMapping("/new-book")
    public String newBook() {
        return "new-book";
    }

    @GetMapping("/details")
    public String details() {
        return "book-details";
    }

    @PostMapping("/create-book")
    public String addBook(@ModelAttribute BookStore bookStore) {
        service.createBook(bookStore);
        System.out.println(bookStore);
        return "new-book";
    }

}
