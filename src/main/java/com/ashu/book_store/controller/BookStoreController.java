package com.ashu.book_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashu.book_store.model.BookStore;
import com.ashu.book_store.service.BookStoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookStoreController {
    private final BookStoreService service;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        model.addAttribute("books", service.getBooksNameandId());
        return "home";
    }

    @GetMapping("/new-book")
    public String newBook() {
        return "new-book";
    }

    @GetMapping("/details")
    public String details(@RequestParam int id , Model model) {
        var book = service.getBook(id);
        model.addAttribute("book",book);
        return "book-details";
    }

    @PostMapping("/create-book")
    public String addBook(@ModelAttribute BookStore bookStore) {
        service.createBook(bookStore);
        System.out.println(bookStore);
        return "new-book";
    }

}
