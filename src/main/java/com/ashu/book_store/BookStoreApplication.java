package com.ashu.book_store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ashu.book_store.service.FileService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class BookStoreApplication implements CommandLineRunner {
	private final FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		fileService.createDirectory();
	}
}
