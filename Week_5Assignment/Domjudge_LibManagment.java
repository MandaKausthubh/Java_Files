import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Book {
    private int BookID;
    private String BookTitle;
    private String BookAuthor;
    private int BookQuantity;

    // Getters:
    public int GetBookID() {return BookID;}
    public int GetQuantity() {return BookQuantity;}
    
    // Setters:
    public void SetBookID(int ID) {this.BookID = ID;}

    // Constructors:
    public Book(int ID, String Bookname, String BookAuthor, int Quantity) {
        this.BookID = ID;
        this.BookTitle = Bookname;
        this.BookAuthor = BookAuthor;
        this.BookQuantity = Quantity;
    }

    // Book Borrowing:
    public void BookBorrowed() {BookQuantity--;}
    public void BookReturned() {BookQuantity++;}

    public String DisplayBook() {return "Book ID: " + (BookID) + ", Title: \"" + BookTitle + "\", Author: \"" + BookAuthor + "\"\n";}
};


class Student {
    private int StudentID;
    private String StudentName;
    private Map<Integer, Book> MapOfBooksBorrowed = new LinkedHashMap<>();
    
    public static Map<Integer, Student> ListOfAllStudents = new LinkedHashMap<>();   //This is to implement Aggregation

    // Getters : 
    public Student(int ID, String Name) {
        this.StudentID = ID;
        this.StudentName = Name;
        ListOfAllStudents.put(ID, this);
    }
    
    public int GetNumberOfBooksBorrowed() {return MapOfBooksBorrowed.size();}

    public void BorrowBook(Book BorrowedBook) {
        // System.out.println("Book :" + BorrowedBook.GetBookID());
        MapOfBooksBorrowed.put(BorrowedBook.GetBookID(), BorrowedBook);
    }

    public void ReturnBook(int BID) {
        for(Map.Entry<Integer, Book> ent: MapOfBooksBorrowed.entrySet()) {
            if(ent.getKey() == BID) {
                MapOfBooksBorrowed.remove(BID); break;
            }
        }
    }

    public boolean CheckIfBookBorrowed(int BID) {
        return MapOfBooksBorrowed.containsKey(BID);
    }

    public String DisplayStudent() {
        String ans = "Books borrowed by student " + StudentID + ":\n";
        if(MapOfBooksBorrowed.size() == 0) return ans + "No books borrowed.\n";
        for(Map.Entry<Integer, Book> ent: MapOfBooksBorrowed.entrySet()) {
            ans += ent.getValue().DisplayBook();
        }
        return ans;
    }
}


class LibraryTransaction {
    private int Type;
    private int BID;
    private int SID;

    public LibraryTransaction(int Type, int BID, int SID) {this.Type = Type; this.BID = BID; this.SID = SID;}
    
}


class Library {
    private Set<Integer> ListOfStudentsRegistered = new LinkedHashSet<>();
    private Map<Integer, Book> ListOfBooks = new LinkedHashMap<>();
    private int MaxLimit = 3;
    
    public void AddStudent(int SID, String Name) {
        Student NewStudent = new Student(SID, Name);
        ListOfStudentsRegistered.add(SID);
    }

    public void AddBooks(int BID, String Title, String Author, int Quantity) {
        Book NewBook = new Book(BID, Title, Author, Quantity);
        ListOfBooks.put(BID, NewBook);
    }

    public void AddTransaction(int BID, int SID, int Type) {
        if(Type == 1) {
            if(Student.ListOfAllStudents.get(SID).GetNumberOfBooksBorrowed() < MaxLimit && ListOfBooks.get(BID).GetQuantity() > 0) {
                LibraryTransaction NewTransaction = new LibraryTransaction(Type, BID, SID);
                // System.out.println("Borrowing A Book");
                Student.ListOfAllStudents.get(SID).BorrowBook(ListOfBooks.get(BID));
                ListOfBooks.get(BID).BookBorrowed();

            }
        }
        else if(Type == 2) {
            if(Student.ListOfAllStudents.get(SID).CheckIfBookBorrowed(BID)) {
                ListOfBooks.get(BID).BookReturned();
                Student.ListOfAllStudents.get(SID).ReturnBook(BID);
            }
        }
    }

    public String Display() {
        String ans = "";
        for(int i: ListOfStudentsRegistered) {
            ans += Student.ListOfAllStudents.get(i).DisplayStudent();
        }
        return ans;
    }
};


class Domjudge_LibManagment {
    public static void main(String Args[]) {
        int n, m, q;
        Scanner scan = new Scanner(System.in);
        String str[] = scan.nextLine().split(" ", 3);
        n = Integer.parseInt(str[0]); m = Integer.parseInt(str[1]); q = Integer.parseInt(str[2]);
        
        Library IIITBLib = new Library();

        for(int i = 0; i < n; i++) {
            str = scan.nextLine().split("\"", 5);
            int BID = Integer.parseInt(str[0].split(" ",2)[0]);
            String Title = (str[1]);
            String Author = (str[3]);
            int Quant = Integer.parseInt(str[4].split(" ",2)[1]);

            IIITBLib.AddBooks(BID, Title, Author, Quant);
        }

        for(int i = 0; i < m; i++) {
            str = scan.nextLine().split("\"", 5);
            int SID = Integer.parseInt(str[0].split(" ", 2)[0]);
            String Name = (str[1]);
            IIITBLib.AddStudent(SID, Name);
        }

        for(int i = 0; i < q; i++) {
            str = scan.nextLine().split(" ", 5);
            int Type = Integer.parseInt(str[0]);
            int BID = Integer.parseInt(str[1]);
            int SID = Integer.parseInt(str[2]);
            IIITBLib.AddTransaction(BID, SID, Type);
        }

        System.out.print(IIITBLib.Display());
        scan.close();
    }
}









