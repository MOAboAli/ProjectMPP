package dataaccess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.*;
import dataaccess.DataAccessFacade.StorageType;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS,CheckoutRecord,CheckoutEntry;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir")+ "/dataaccess/storage"; //\src
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	//implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);	
	}

	public void saveNewCheckEntry(CheckoutEntry Entry) {
	    HashMap<String, CheckoutEntry> Ents = readCheckoutEntryMap()	;
		String entryId = Entry.getCheckoutDate().toString();
		Ents.put(entryId, Entry);
		saveToStorage(StorageType.CheckoutEntry, Ents);
	}

	public void saveNewCheckRecord(CheckoutRecord Record) {
		HashMap<String, CheckoutRecord> Records = readCheckoutRecord()	;
		String entryId = Record.getLibraryMember().getMemberId();
		Records.put(entryId, Record);
		saveToStorage(StorageType.CheckoutEntry, Records);
	}

	public void saveNewBook(Book Record) {
		HashMap<String, Book> Records = readBooksMap()	;
		String entryId = Record.getIsbn();
		Records.put(entryId, Record);
		saveToStorage(StorageType.BOOKS, Records);
	}
	

	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
	}

	public HashMap<String, CheckoutEntry> readCheckoutEntryMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, CheckoutEntry>) readFromStorage(StorageType.CheckoutEntry);

	}

	public HashMap<String, CheckoutRecord> readCheckoutRecord() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, CheckoutRecord>) readFromStorage(StorageType.CheckoutRecord);

	}


	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	static void loadCheckoutEntryMap(List<CheckoutEntry> List) {
		HashMap<String, CheckoutEntry> mainlits = new HashMap<String, CheckoutEntry>();
		List.forEach(item -> mainlits.put(item.getCheckoutDate().toString(), item));
		saveToStorage(StorageType.CheckoutEntry, mainlits);
	}

	static void loadCheckoutRecordMap(List<CheckoutRecord> List) {
		HashMap<String, CheckoutRecord> mainlits = new HashMap<String, CheckoutRecord>();
		List.forEach(item -> mainlits.put(item.getLibraryMember().getMemberId(), item));
		saveToStorage(StorageType.CheckoutRecord, mainlits);
	}

	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			if (!Files.exists(path)) {
				Files.createFile(path);
			}
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				if(out != null) {
					try {
						out.close();
					} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}


	
}
