package Exception;

import business.LibrarySystemException;

public class MemberNotFoundException extends LibrarySystemException {

    public MemberNotFoundException() {
        super("No Member was found....................");
    }
}
