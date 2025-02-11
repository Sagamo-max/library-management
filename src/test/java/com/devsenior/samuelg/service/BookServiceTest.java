package com.devsenior.samuelg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.samuelg.exceptions.NotFoundException;

public class BookServiceTest {

    private BookService service;

    @BeforeEach
    void setUp() {
        service = new BookService();
    }

    @Test
    void testAddBook() throws NotFoundException {
        // Given
        var isbn = "1234567890";
        var title = "Aprendiendo Java";
        var author = "Samuel Gaviria";

        // When
        service.addBook(isbn, title, author);

        // Then
        var book = service.getBookByIsbn(isbn);
        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());

    }

    @Test
    void testDeleteExistingBook() throws NotFoundException {
        // Given
        var isbn = "1234567890";
        var title = "Aprendiendo Java";
        var author = "Samuel Gaviria";
        service.addBook(isbn, title, author);

        // When
        service.deleteBook(isbn);

        // Then
        // No hay forma de verificar que el libro fue eliminado
        try {
            service.getBookByIsbn(isbn);
            fail();
        } catch (NotFoundException e) {
            assertTrue(true);
        }

    }

    @Test
    void testDeleteNonExistingBook() throws NotFoundException {
        // Given
        var isbn = "1234567890";

        // When - Then
        assertThrows(NotFoundException.class, 
                () -> {
                service.deleteBook(isbn);
                });
        
        
    }

    @Test
    void testGetAllBooks() {

    }

    @Test
    void testGetBookByIsbn() {

    }
}
