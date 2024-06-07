package business.rulesets;


import business.Author;
import business.Book;
import librarysystem.AddBookWindow;
import librarysystem.AddNewMemberWindow;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Rules:
 * 1. All fields must be nonempty
 * 2. Price must be a floating point number with two decimal places
 * 3. Price must be a number greater than 0.49.
 */

public class AddBookRuleSet implements RuleSet {

    @Override
    public void applyRules(Component ob) throws RuleException {

        AddBookWindow addBookWindow = (AddBookWindow) ob;
        for (JTextField field: AddBookWindow.INSTANCE.getAllFields()){
            if (RuleSetFactory.isFieldsHasEmptyValue(field))
                throw new RuleException("All Field Can't be Empty");
        }

        //if (!(ob instanceof Book)) {
        //    throw new RuleException("Invalid component type");
        //}

        //Book book = (Book) ob;
/*
        // Rule 1: All fields must be nonempty
        if (book.getIsbn() == null || book.getIsbn().isEmpty() ||
                book.getTitle() == null || book.getTitle().isEmpty() ||
                book.getAuthors() == null || book.getAuthors().isEmpty() ||
                book.getMaxCheckoutLength() == 0) {
            throw new RuleException("All fields must be nonempty");
        }

        // Rule 2: ISBN must be a valid 10 or 13 digit number
        if (!isValidIsbn(book.getIsbn())) {
            throw new RuleException("ISBN must be a valid 10 or 13-digit number");
        }

        // Rule 3: maxCheckoutLength should be either 7 or 21
        if (book.getMaxCheckoutLength() != 7 && book.getMaxCheckoutLength() != 21) {
            throw new RuleException("Borrow duration must be 7 or 21 days");
        }

        // Rule 4: There should be at least one author
        List<Author> authors = book.getAuthors();
        if (authors == null || authors.isEmpty()) {
            throw new RuleException("At least one author must be specified");
        }


 */
    }

    private boolean isValidIsbn(String isbn) {
        return isbn.matches("\\d{10}") || isbn.matches("\\d{13}");
    }
}
