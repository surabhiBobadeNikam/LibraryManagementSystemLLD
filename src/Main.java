import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.model.Library;
import com.libraryManagementSystem.model.Patron;
import com.libraryManagementSystem.notification.EmailNotifier;
import com.libraryManagementSystem.strategy.AuthorSearchStrategy;
import com.libraryManagementSystem.strategy.ISBNSearchStrategy;
import com.libraryManagementSystem.strategy.TitleSearchStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmailNotifier emailNotifier = new EmailNotifier();
        Library library = new Library(emailNotifier);

        Book book1 = new Book("Harry Potter and Goblet of Fire", "J. K. Rowling", "1234", 1999);
        Book book2 = new Book("The Da Vinci Code", "Dan Brown", "5678", 2005);
        Book book3 = new Book("Harry Potter and Goblet of Fire", "J. K. Rowling", "34565", 1999);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        Patron patron1 = new Patron(1001, "Surabhi Bobade");
        Patron patron2 = new Patron(1002, "Rohit Nikam");

        library.addPatron(patron1);
        library.addPatron(patron2);

        System.out.println("\n================ LENDING PROCESS ================");

        library.checkoutBook(1001, book1.getIsbn());

        library.checkoutBook(1002, book1.getIsbn());

        library.checkoutBook(1001, book2.getIsbn());

        System.out.println("\n================ SEARCH FUNCTIONALITY ================");

        List<Book> searchResults;

        // Search by Title
        library.setSearchStrategy(new TitleSearchStrategy());
        searchResults = library.searchBook("Harry");
        System.out.println("Title Search 'harry': " + searchResults);

        // Search by Author
        library.setSearchStrategy(new AuthorSearchStrategy());
        searchResults = library.searchBook("Brown");
        System.out.println("Author Search 'Brown': " + searchResults);

        // Search by ISBN
        library.setSearchStrategy(new ISBNSearchStrategy());
        searchResults = library.searchBook(book3.getIsbn());
        System.out.println("ISBN Search '" + book3.getIsbn() + "': " + searchResults);

        System.out.println("\n================ RETURN & NOTIFICATION ================");

        library.returnBook(1001, book1.getIsbn());

        library.checkoutBook(1002, book1.getIsbn());

        System.out.println("\n================ PATRON HISTORY ================");
        List<String> history = library.getPatronHistory(1001);
        System.out.println("Alice's Borrowing History (ISBNs): " + history);
    }
}