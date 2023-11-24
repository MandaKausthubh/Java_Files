import java.util.*;

class Book {
    private int ISBN;
    private static int CurrentIndex = 0;
    private String bookName;
    private boolean available;
    private String author;
    private int index;
    public static List<Book> UniversalListOfBooks = new ArrayList<>();

    public int getISBN() {return ISBN;}

    public String getBookAuthor() {return author;}

    public String getBookName() {return bookName;}

    public boolean getAvailability() {return available;}

    public int getIndex() {return index;}

    public void setBookName(String name) {this.bookName = name;}

    public String displayBookDetails() {
        return bookName + '\n' + author + '\n' + ISBN + '\n';
    }

    public Book(String name, String author, int ID) {
        this.ISBN = ID;
        this.bookName = name;
        this.available = true;
        this.author = author;
        UniversalListOfBooks.add(this);
        this.index = UniversalListOfBooks.size() - 1;
    }
}

class Library {
    private int libraryID;
    private Map<Integer, Integer> listOfBooks;

    public int getLibraryID() {return libraryID;}

    public Library() {this.listOfBooks = new HashMap<>();}

    public void addBook(String name, String author, int ID) {
        Book newBook = new Book(name, author, ID);
        listOfBooks.put(newBook.getISBN(), newBook.getIndex());
    }

    public void removeBook(int ISBN) {
        listOfBooks.remove(ISBN);
    }

    public String displayLibraryDetails() {
        StringBuilder details = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : listOfBooks.entrySet()) {
            details.append(Book.UniversalListOfBooks.get(entry.getValue()).displayBookDetails());
        }
        return details.toString();
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Library IIITB_Lib = new Library();
        Scanner scanner = new Scanner(System.in);
        int test = scanner.nextInt();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < test; i++) {
            int command = scanner.nextInt();
            if (command == 1) {
                ans.append("Books Added:\n");
                int n = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                for (int j = 0; j < n; j++) {
                    //scanner.nextLine(); // Consume newline
                    String bookName = scanner.nextLine();
                    String author = scanner.nextLine();
                    int ISBN = Integer.parseInt(scanner.nextLine());
                    ans.append(ISBN).append('\n');

                    IIITB_Lib.addBook(bookName, author, ISBN);
                }
            } else if (command == 2) {
                ans.append("Books removed:\n");
                int n = scanner.nextInt();
                for (int j = 0; j < n; j++) {
                    int ISBN = scanner.nextInt();
                    ans.append(ISBN).append('\n');
                    IIITB_Lib.removeBook(ISBN);
                }
            } else if (command == 3) {
                ans.append("Books:\n");
                ans.append(IIITB_Lib.displayLibraryDetails());
            }
        }
        scanner.close();
        System.out.println(ans.toString());
    }
}
