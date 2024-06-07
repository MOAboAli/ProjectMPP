package business;

import Exception.AddNewMemberException;
import Exception.BookNotFoundException;
import Exception.MemberNotFoundException;
import Exception.NoBooksCopiesException;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}


    public void addNewMember(
            String street,
            String city,
            String state,
            String zip,
            String memberID,
            String fname, String lname, String tel) throws AddNewMemberException {
        DataAccess da = new DataAccessFacade();
        if (da.readMemberMap().containsKey(memberID))
            throw new AddNewMemberException("This member ID already exist");
        Address address = new Address(street, city, state, zip);
        LibraryMember libraryMember = new LibraryMember(memberID, fname, lname, tel, address);
        da.saveNewMember(libraryMember);
    }


    @Override
    public void addBook(Book book) {
        List<Book> books = new ArrayList<>();
        DataAccess da = new DataAccessFacade();
        da.addBook(book);
    }



    //////////////////////Book Check Out///////////////////////////

    public void CheckBook(String BookISBN) throws BookNotFoundException {
        if( !allBookIds().contains(BookISBN))
            throw new BookNotFoundException();
    }

    public void CheckMemeber(String MemberID) throws MemberNotFoundException {
        if( ! allMemberIds().contains(MemberID))
            throw new MemberNotFoundException();
    }

    public BookCopy CheckAvailability(String BookID) throws NoBooksCopiesException {
        DataAccessFacade DataAccess =new DataAccessFacade();
        HashMap<String,Book> books = DataAccess. readBooksMap();
        Book book = books.get(BookID);
        if(book == null)
            throw new NoBooksCopiesException();
        if(!book.isAvailable())
            throw new NoBooksCopiesException();
        return book.getNextAvailableCopy();
    }


    public CheckoutEntry PutCheckOutEntry(BookCopy bookcopy,String MemberID) throws MemberNotFoundException {

        DataAccessFacade DataAccess =new DataAccessFacade();
        HashMap<String, CheckoutRecord> Records = DataAccess.readCheckoutRecord();
        CheckoutRecord checkoutrecord = Records.get(MemberID);
        if(checkoutrecord == null){
            LibraryMember member = DataAccess.readMemberMap().get(MemberID);
            if(member == null)
                throw  new MemberNotFoundException();
            checkoutrecord = new CheckoutRecord(member);
        }

        CheckoutEntry checkoutentry = new CheckoutEntry(LocalDate.now(),bookcopy,checkoutrecord);
        checkoutrecord.addCheckoutEntry(checkoutentry);
        DataAccess.saveNewCheckRecord(checkoutrecord);
        DataAccess.saveNewCheckEntry(checkoutentry);
        bookcopy.changeAvailability();
        DataAccess.saveNewBook(bookcopy.getBook());
        return checkoutentry;
    }
	
}
