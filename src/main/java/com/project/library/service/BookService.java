package com.project.library.service;

import com.project.library.model.Book;
import com.project.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepo repo;

    public Book addBook(Book book, MultipartFile file) throws IOException {
        System.out.println(book.getBookName());
        book.setBookImageName(file.getName());
        book.setBookImage(file.getBytes());
        book.setBookImageType(file.getContentType());
        repo.save(book);
        return book;
    }

    public List<Book> getBooks() {
        return repo.findAll();
    }

    public Book updateBook(Book book, int id, MultipartFile file) throws IOException {
        Book existingBook = repo.findById(id).orElse(null);
        if (existingBook != null) {

           book.setBookImage(file.getBytes());
           book.setBookImageName(file.getName());
           book.setBookImageType(file.getContentType());
            repo.save(book);
        } else {
            return null;
        }
        return book;
    }

    public Book getBookById(int id) {
        return repo.findById(id).orElse(null);
    }

    public boolean deletebookByid(int id) {
        Book existingBook = repo.findById(id).orElse(null);
        if (existingBook != null) {
            repo.delete(existingBook);
            return true;
        }
        return false;

    }

    public List<Book> search(String keyword) {
        return repo.findByKeyword(keyword);
    }


}
