package com.ashu.book_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashu.book_store.model.BookStore;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookStoreController {

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

        System.out.println(bookStore);
        return "new-book";
    }

}
