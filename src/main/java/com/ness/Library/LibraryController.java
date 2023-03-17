package com.ness.Library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

    @RestController
    @RequestMapping("/library")
    public class LibraryController {

    private List<Book> books = new ArrayList<>();

    // Adding a new book – will add a new book to list of library books
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        books.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    //Get all books from the library sorted by the author and by their title (in case an author has more books)
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {

    List<Book> sortedBooks;

        sortedBooks = books.stream()
                .sorted(Comparator.comparing(Book::getTitle).thenComparing(Book::getAuthor))
                .collect(Collectors.toList());
    
    return new ResponseEntity<>(sortedBooks, HttpStatus.OK);
}


    //Delete a book from the library
    @DeleteMapping("/books/{title}")
    public ResponseEntity<?> deleteBook(@PathVariable String title) {

        boolean isRemoved = books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));

        if (isRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Search for a book by title
    @GetMapping("/books/search/{title}")
    public ResponseEntity<List<Book>> searchBooksByTitle(@PathVariable String title) {

        List<Book> matchingBooks = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(matchingBooks, HttpStatus.OK);
    }

    //Update the author for a book
    @PatchMapping("/books/{title}")
    public ResponseEntity<Book> updateBookAuthor(@PathVariable String title, @RequestBody Book book) {

        Book updatedBook = books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .map(b -> {
                    b.setAuthor(book.getAuthor());
                    return b;
                })
                .orElse(null);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
