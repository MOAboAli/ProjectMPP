package business;



import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {


    private final BookCopy bookCopy;
    private final CheckoutRecord checkoutRecord;
    private final LocalDate checkoutDate;
    private LocalDate dueDate;

    public CheckoutEntry(LocalDate checkoutDate, BookCopy bookCopy, CheckoutRecord checkoutRecord) {
        this.checkoutDate = checkoutDate;
        this.bookCopy = bookCopy;
        this.checkoutRecord = checkoutRecord;
        setDueDate(
                LocalDate.now().plusDays(-1)
        );
    }

    void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public String getBookIsbnNumber(){
        return  bookCopy.getBook().getIsbn();
    }

    public int getBookCopyNumber(){
        return  bookCopy.getCopyNum();
    }

    public  String getLibraryMemberFullName(){
        final var firstName = checkoutRecord.getLibraryMember().getFirstName();
        final var lastName = checkoutRecord.getLibraryMember().getLastName();

        return String.format("%s %s", firstName, lastName);
    }

    public BookCopy getBookCopy(){
        return bookCopy;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }


}
