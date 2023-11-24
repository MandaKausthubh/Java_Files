import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


class Book{
    private int ISBN;
    private String BookName;
    private String BookAuthor;
    private int Quantity;
    
    public static Map<Integer, Book> UniversalMapOfBooks = new LinkedHashMap<>();
    
    public Book(int ID, String Title, String Author, int Quantity) {
        this.ISBN = ID;
        this.BookName = Title;
        this.BookAuthor = Author;
        this.Quantity = Quantity;
        UniversalMapOfBooks.put(ID, this);
    }

    public int GetISBN() {return ISBN;}
    public int GetQuantity() {return Quantity;}
    public String GetTitle() {return BookName;}
    public String GetAuthor() {return BookAuthor;}

    public void BooksBorrowedByStudent() {Quantity--;}
    public void BookReturned() {Quantity ++;}
    
    public boolean CheckAvailable() {return Quantity > 0;}

    public String DisplayDetails() {return "BookID: " + String.valueOf(ISBN) + ", Title: \"" + BookName + "\", Author: \"" + BookAuthor + "\"\n";}
};

class Student{
    private int StudentID;
    private String StudentName;
    private Set<Integer> BooksBorrowedByStudent = new LinkedHashSet<>();
    private int MaxLimitToBorrowing = 3;

    public Student(int ID, String StudentName) {
        this.StudentID = ID;
        this.StudentName = StudentName;
    }

    public int GetNumberOfBooksBorrowed() {return BooksBorrowedByStudent.size();}

    public void AddNewBook(int ID) {
        BooksBorrowedByStudent.add(ID);
    }

    public void RemoveBook(int ID) {
        if(BooksBorrowedByStudent.contains(ID))
            BooksBorrowedByStudent.remove(ID);
    }

    public String Display() {
        String ans = "";
        ans += ("Books borrowed by student " + StudentID + ":\n");
        // System.out.println(BooksBorrowedByStudent.size());
        if(BooksBorrowedByStudent.size() == 0) return ans + "No books borrowed.\n";
        for(int i: BooksBorrowedByStudent) {
            ans += Book.UniversalMapOfBooks.get(i).DisplayDetails();
        }
        return ans;
    }
}

class LibraryTransaction{
    private int type;
    private int BookID;
    private int StudentID;

    public LibraryTransaction(int type, int BID, int SID) {
        this.type = BID;
        this.StudentID = SID;
        this.BookID = BID;
    }
};

class Library {
    private int LibraryID;
    private Set<Integer> ListOfBooks = new HashSet<Integer>();
    private Map<Integer, Student> ListOfStudents = new LinkedHashMap<>();
    private Map<Integer, LibraryTransaction> ListOfLibTransactions = new LinkedHashMap<>();
    private int transactionIndex = 0;
    private int MaxLimitToBorrowing = 3;
    
    public void AddBookToLib(int ID) {
        ListOfBooks.add(ID);
    }

    public void AddStudentToLib(int ID, String StudentName) {
        Student NewStudent = new Student(ID, StudentName);
        ListOfStudents.put(ID, NewStudent);
    }

    public void AddTransaction(int Type, int SID, int BID) {
        if(Type == 1) {
            if(ListOfStudents.get(SID).GetNumberOfBooksBorrowed() < MaxLimitToBorrowing && Book.UniversalMapOfBooks.get(BID).CheckAvailable()) {
                LibraryTransaction NewTransaction = new LibraryTransaction(Type, BID, SID);
                ListOfLibTransactions.put(transactionIndex++, NewTransaction);
                ListOfStudents.get(SID).AddNewBook(BID); Book.UniversalMapOfBooks.get(BID).BooksBorrowedByStudent();
            }
        }
        else if(Type == 2) {
            LibraryTransaction NewTransaction = new LibraryTransaction(Type, BID, SID);
            ListOfLibTransactions.put(transactionIndex++, NewTransaction);
            ListOfStudents.get(SID).RemoveBook(BID);
            Book.UniversalMapOfBooks.get(BID).BookReturned();
        }
    }

    public String DisplayLibStudentData() {
        String ans = "";
        for(Map.Entry<Integer, Student> Entry: ListOfStudents.entrySet()) {
            ans += Entry.getValue().Display();
        }
        return ans;
    }
};

class LibraryManagement2{
    public static void main(String args[]){
        Library IIITBLib = new Library();
        Scanner scan = new Scanner(System.in);
        String[] str1 = scan.nextLine().split(" ");
        int n = Integer.parseInt(str1[0]), m = Integer.parseInt(str1[1]), q = Integer.parseInt(str1[2]);

        for(int i = 0; i < n; i++) {
            String[] str = scan.nextLine().split("\"", 5);
            int BID, Quant;
            String Title, Author;
            Title = str[1]; Author = str[3];
            BID = Integer.parseInt(str[0].split(" ")[0]);
            Quant = Integer.parseInt(str[4].split(" ")[1]);
            Book NewBook = new Book(BID, Title, Author, Quant);
            IIITBLib.AddBookToLib(BID);
        }

        for(int i = 0; i < m; i++) {
            String str = scan.nextLine();
            int SID = Integer.parseInt((str.split("\"", 2)[0]).split(" ")[0]);
            String Name = str.split("\"",2)[1];
            IIITBLib.AddStudentToLib(SID, Name);
        }

        for(int i = 0; i < q; i++) {
            String[] str = scan.nextLine().split(" ", 3);
            int Type = Integer.parseInt(str[0]), SID = Integer.parseInt(str[2]), BID = Integer.parseInt(str[1]);
            IIITBLib.AddTransaction(Type, SID, BID);
        }

        // System.out.println("a" + Book.UniversalMapOfBooks.size());
        System.out.print(IIITBLib.DisplayLibStudentData());
        /* int n; String S = "5 ";
        n = Integer.parseInt(S.split(" ", 2)[0]);
        System.out.println(n);
        Map<Integer, Integer> Hi = new LinkedHashMap<>(); */
        scan.close();
    }
}













