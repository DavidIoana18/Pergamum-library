package com.ness.Library;

public class BookNotFoundException  extends RuntimeException{
    BookNotFoundException(Long id) {
        super("Could not find book " + id);
    }
}
