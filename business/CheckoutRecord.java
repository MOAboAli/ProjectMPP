package business;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements  Cloneable, Serializable {
    private final List<CheckoutEntry> checkoutEntries;
    private final LibraryMember libraryMember;

    public CheckoutRecord (LibraryMember libraryMember){
        this.libraryMember = libraryMember;
        this.checkoutEntries = new ArrayList<>();
    }

    List<CheckoutEntry> getCheckoutEntries() {
        return checkoutEntries;
    }

    void addCheckoutEntry(CheckoutEntry entry){
        checkoutEntries.add(entry);
    }

    public LibraryMember getLibraryMember(){
        return libraryMember;
    }

    public List<CheckoutEntry> readCheckoutEntries() {
        return List.copyOf(getCheckoutEntries());
    }

    @Override
    public CheckoutRecord clone() {
        try {
            return (CheckoutRecord) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
