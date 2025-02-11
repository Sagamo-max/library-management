package com.devsenior.samuelg.service;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.samuelg.exceptions.NotFoundException;
import com.devsenior.samuelg.model.Loan;
import com.devsenior.samuelg.model.LoanState;

public class LoanSerice {
    private List<Loan> loans;
    private BookService bookService;
    private UserService userService;

    public LoanSerice(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loans = new ArrayList<>();
    }

    public void addLoan(String id, String isbn) throws NotFoundException {
        var user = userService.getUserById(id);
        var book = bookService.getBookByIsbn(isbn);
        loans.add(new Loan(user, book));
    }

    public void returnBook(String id, String isbn) throws NotFoundException {
        for (var loan : loans) {
            if (loan.getUser().getId().equals(id) 
            && loan.getBook().getIsbn().equals(isbn)
            && loan.getLoanDate().equals(LoanState.STARTED)) {
                loan.setState(LoanState.FINISHED);
                loans.remove(loan);
                return ;
            }
        }
        throw new NotFoundException("No hay un prestamo del libro ISBN: " + isbn + " al usuario con ID: " + id);
    }
}
