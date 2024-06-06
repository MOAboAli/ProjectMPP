package Exception;

import business.LibrarySystemException;

public class BookNotFoundException extends LibrarySystemException {

    public BookNotFoundException() {
        super("No Book was found....................");
    }
}
