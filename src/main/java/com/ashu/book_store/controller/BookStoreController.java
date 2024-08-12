package com.ashu.book_store.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashu.book_store.dto.BookDetailsResponse;
import com.ashu.book_store.model.BookStore;
import com.ashu.book_store.service.BookStoreService;
import com.ashu.book_store.service.FileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookStoreController {
    private final BookStoreService service;
    private final FileService fileService;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        model.addAttribute("books", service.getBooksNameandId());
        return "home";
    }

    @GetMapping("/new-book")
    public String newBook(Model model) {
        model.addAttribute("book", new BookDetailsResponse());
        return "new-book";
    }

    @GetMapping("/details")
    public String details(@RequestParam int id , Model model) {
        var book = service.getBook(id);
        model.addAttribute("book",book);
        return "book-details";
    }

    @PostMapping("/create-update-book")
    public String addBook(@ModelAttribute BookStore bookStore , @RequestParam int bookId ) throws IOException{
        if(bookId == 0){
            String path = fileService.uploadImage(bookStore.getBookImage());
            service.createBook(bookStore,path);
        }
        else {
            service.updateBook(bookId,bookStore);
            service.updateImage(bookId,bookStore.getBookImage());
        }
        return "redirect:/home";
    }
    
    @GetMapping(path= "/book/image/{id}" ,produces = {"image/jpeg","image/jpg","image/png"})
    @ResponseBody
    public byte[] getImage(@PathVariable int id) throws IOException {
        String imageName = service.findImageById(id);
        byte[] image = fileService.getImage(imageName);
        return image;
    }
    @GetMapping("/remove-book")
    public String removeBook(@RequestParam int id){
        service.deleteBookById(id);
        return "redirect:/home";
    }
    @GetMapping("/update-book")
    public String updateBook(@RequestParam int id, Model model) {
        var book = service.getBook(id);
        model.addAttribute("book",book);
        return "new-book";
    }

}
