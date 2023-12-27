package org.softserve.book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class BookCollection {
    //Create set of authors to avoid duplicates
    private Set<String> authors = new HashSet<>();
    private ArrayList<Book> selectedBooks = new ArrayList<>();

    public Set<String> getBooksAuthors(ArrayList<Book> books) {
        authors.clear();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }

    public Set<String> getBooksAuthorsByGenre(ArrayList<Book> books, String genre) {
        authors.clear();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                authors.add(book.getAuthor());
            }
        }
        return authors;
    }

    public Set<String> getBooksAuthorsByYear(ArrayList<Book> books, int year) {
        authors.clear();
        for (Book book : books) {
            if (book.getYear() == year) {
                authors.add(book.getAuthor());
            }
        }
        return authors;
    }

    public ArrayList<Book> selectBooksByAuthor(ArrayList<Book> books, String author) {
        selectedBooks.clear();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }

    public ArrayList<Book> selectBooksByYear(ArrayList<Book> books, int year) {
        selectedBooks.clear();
        for (Book book : books) {
            if (book.getYear() == year) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }

    public ArrayList<Book> selectBooksByGenre(ArrayList<Book> books, String genre) {
        selectedBooks.clear();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }

    public ArrayList<Book> removeBooksByAuthor(ArrayList<Book> books, String author) {
        books.removeIf(book -> book.getAuthor().equalsIgnoreCase(author));
        return books;
    }

    public ArrayList<Book> sortByComparator (ArrayList<Book> books, Comparator<Book> comparator){
        books.sort(comparator);
        return books;
    }

    public ArrayList<Book> mergeTwoCollections(ArrayList<Book> books1, ArrayList<Book> books2) {
        books1.addAll(books2);
        return books1;
    }

    static class BookCollectionMain {
        public static void main(String[] args) {
            //Create bookCollection
            ArrayList<Book> books = CreateBook.createBookCollection();

            //Create the object of BookCollection
            BookCollection collection=new BookCollection();

            //1. Print the list of all authors in the collection to the console.
            System.out.println("Authors: " + collection.getBooksAuthors(books));

            //2. Print the list of authors who have written books in a given genre.
            String genre = "Adventure";
            System.out.println("\n" + genre + " genre Authors:");
            System.out.println(collection.getBooksAuthorsByGenre(books, genre));


            //3. Print the list of authors whose books were published in a given year.
            int year = 2020;
            System.out.println("\nAuthors whose books were published in a " + year + ":");
            System.out.println(collection.getBooksAuthorsByYear(books, year));

            //4. Find a book in the collection written by a given author.
            String author = "Jane Austen";

            //Create Books Collection to store selected books
            ArrayList<Book> newBooksCollection = new ArrayList<>();

            newBooksCollection = collection.selectBooksByAuthor(books, author);
            System.out.println("\nBooks of " + author + ":");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }

            //5. Find all books that were written in a given year.
            newBooksCollection = collection.selectBooksByYear(books, year);
            System.out.println("\nBooks published in a " + year + ":");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }

            //6. Find all books that belong to a given genre.
            newBooksCollection = collection.selectBooksByGenre(books, genre);
            System.out.println("\n" + genre + " genre Books:");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }

            //7. Remove from the collection all books written by a given author.
            collection.removeBooksByAuthor(books, author);
            System.out.println("\nBooks collection after deletion books of " + author + ":");
            for (Book book : books) {
                System.out.println(book);
            }

            //8. Sort the book collection by a given criterion (e.g., title, author, or year of publication).
            //Create appropriate Comparators
            Comparator<Book> byTitle = Comparator.comparing(Book::getTitle);
            Comparator<Book> byAuthor = Comparator.comparing(Book::getAuthor);
            Comparator<Book> byGenre = Comparator.comparing(Book::getGenre);
            Comparator<Book> byYear = Comparator.comparing(Book::getYear);

            //sort by Title
            collection.sortByComparator(books,byTitle);
            //books.sort(byTitle);
            System.out.println("\nBooks collection sorted by Tittle:");
            for (Book book : books) {
                System.out.println(book);
            }

            //sort by Author
            collection.sortByComparator(books,byAuthor);
            //books.sort(byAuthor);
            System.out.println("\nBooks collection sorted by Author:");
            for (Book book : books) {
                System.out.println(book);
            }

            //sort by Genre
            collection.sortByComparator(books,byGenre);
            //books.sort(byGenre);
            System.out.println("\nBooks collection sorted by Genre:");
            for (Book book : books) {
                System.out.println(book);
            }

            //sort by Year
            collection.sortByComparator(books,byYear);
            //books.sort(byYear);
            System.out.println("\nBooks collection sorted by Year:");
            for (Book book : books) {
                System.out.println(book);
            }

            //9. Combine two book collections into one.
            //Create new books Collection
            ArrayList<Book> newCollection = CreateBook.createNewBookCollection();
            System.out.println("\nBooks newCollection:");
            for (Book book : newCollection) {
                System.out.println(book);
            }

            //Add books from both collection to new one
            ArrayList<Book> mergedBookCollection = collection.mergeTwoCollections(books, newCollection);

            System.out.println("\nBooks collection after merge with another Book Collection:");
            for (Book book : mergedBookCollection) {
                System.out.println(book);
            }

            //10. Create a subCollection of books from a given genre.
            newBooksCollection = collection.selectBooksByGenre(mergedBookCollection, genre);
            System.out.println("\n" + genre + " genre Books:");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }
        }
    }
}
