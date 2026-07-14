package com.project.library.controller;


import com.project.library.model.Book;
import com.project.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getBook(){
        List<Book> books = bookService.getBooks();
        if(books.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/")
    public String welcome(){
        return "WELCOME TO THE LIBRARY PROJECT";
    }



    @PostMapping(value = "/books", consumes = {"multipart/form-data"})
    public ResponseEntity<?> addBook(
        @ModelAttribute Book book ,
        @RequestParam(value = "imageFile", required = false) MultipartFile file) throws IOException {
    System.out.println(book.getBookName());
    Book book1 = bookService.addBook(book,file);
    if(book1 == null){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(book1,HttpStatus.CREATED);
}


    @PutMapping(value = "/books/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateBook (
            @ModelAttribute Book book,
            @PathVariable int id,
           @RequestParam(value = "imageFile", required = false) MultipartFile file) throws IOException {
        Book book1 = bookService.updateBook(book,id,file);
        if(book1 == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(book1,HttpStatus.CREATED);
    }



    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookByid(@PathVariable int id){
        Book book = bookService.getBookById(id);
        if(book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }




    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id){
        boolean isDeleted = bookService.deletebookByid(id);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/books/search")
    public  ResponseEntity<?> getBooksByKeyword(@RequestParam("keyword") String keyword){
        List<Book> bookList = bookService.search(keyword);
        if(bookList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }



    @GetMapping("/books/{id}/image")
    public ResponseEntity<byte[]> getBookImage(@PathVariable int id){
       Book book = bookService.getBookById(id);
       if(book != null && book.getBookImage() != null){
           return ResponseEntity.ok()
                   .contentType(MediaType.parseMediaType(book.getBookImageType()))
                   .body(book.getBookImage());
       }
       else return ResponseEntity.notFound().build();

    }
}
