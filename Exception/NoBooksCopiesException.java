package Exception;

import business.LibrarySystemException;

public class NoBooksCopiesException extends LibrarySystemException {

    public NoBooksCopiesException() {
        super("No Available Copies....................");
    }
}
