class Book{
    String title;
    String author;
    int ISBN;
    boolean available;

    static Book ListOfBooks[] = new Book[100];
    static int index = 0;

    Book(String title, String author){
        if(index < 100){
            this.author = author;
            this.title = title;
            ListOfBooks[index++] = this;
            this.available = true;
            this.ISBN = index + 1000;
        }
    }

    void PrintDetails(){
        System.out.print(title+" by "+author); 
        System.out.println("\t ISBN: "+ISBN);
    }
}


class Library{
    int ListOfBooksinLib[] = new int[100];
    static int index = 0;

    public void AddBook(String title, String author) {
        Book NewBook = new Book(title, author);
        ListOfBooksinLib[index++] = Book.index - 1;
        System.out.println("Added:");
        NewBook.PrintDetails();
        System.out.println();
    }

    public void Display(){
        System.out.println("Displaying Library:");
        for(int i = 0; i < index; i++){
            if(ListOfBooksinLib[i] > -1) Book.ListOfBooks[ListOfBooksinLib[i]].PrintDetails();
        }
        System.out.println();
    }

    public void DeleteBook(int ISBN) {
        for(int i = 0; i < index; i++){
            if(Book.ListOfBooks[ListOfBooksinLib[i]].ISBN == ISBN){
                System.out.println("Deleting:");
                Book.ListOfBooks[ListOfBooksinLib[i]].PrintDetails();
                ListOfBooksinLib[i] = -1;
            }
        }
        System.out.println();
    }
}


class main{
    public static void main(String args[])
    {
        Library IIITBLib = new Library();
        IIITBLib.AddBook("Use Of Force", "Bradd Thor");
        IIITBLib.AddBook("Gods of Olympus", "Rick Riordan");
        IIITBLib.AddBook("Tales of Tenali", "Rama Krishna");
        IIITBLib.Display();
        IIITBLib.DeleteBook(1001);
        IIITBLib.Display();
    }
}


