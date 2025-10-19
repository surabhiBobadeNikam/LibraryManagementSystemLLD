Core Features
1. Book Management: Add, remove, and manage books by title, author, and unique ISBN.
2. Patron Management: Register patrons and track their borrowing history.
3. Lending Process: Handle book checkout and return, updating inventory availability.
4. Reservation System: Patrons can reserve books that are currently checked out.

Design Principles and Patterns

1. SOLID Principles
S - Library class manages high-level operations but delegates searching to SearchStrategy implementations
    and notification to the Notifier.
O - Search functionality is open for extension but closed for modification.
    We can add other search strategies by implementing the interface.
L - TitleSearchStrategy, AuthorSearchStrategy, ISBNSearchStrategy can be substituted by SearchStrategy
    interface in Library class.
D - Library class depends on abstractions/ interfaces like SearchStrategy and Notifier
    and not on its concrete implementation.

2. Design Pattern
Strategy Pattern - Used for the book search mechanism. The SearchStrategy interface allows the Library to
        dynamically switch between searching by Title, Author, or ISBN without changing the core search method.

Class Diagram

This diagram illustrates the core classes and their relationships.

![Class Diagram]

