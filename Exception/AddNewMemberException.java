package Exception;

import java.io.Serializable;

public class AddNewMemberException extends Exception implements Serializable {
    public AddNewMemberException() {
    }

    public AddNewMemberException(String message) {
        super(message);
    }

    public AddNewMemberException(Throwable cause) {
        super(cause);
    }
}
