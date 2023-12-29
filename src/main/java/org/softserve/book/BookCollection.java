package org.softserve.book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookCollection {

    public static Set<String> getBooksAuthors(List<Book> books) {
        return books.stream()
        .map(Book::getAuthor)
        .collect(Collectors.toSet());
    }

    public static Set<String> getBooksAuthorsByGenre(List<Book> books, String genre) {
        return books.stream()
        .filter(b -> genre.equalsIgnoreCase(b.getGenre()))
        .map(Book::getAuthor)
        .collect(Collectors.toSet());
    }

    public static Set<String> getBooksAuthorsByYear(List<Book> books, int year) {
        return books.stream()
        .filter(b -> year == b.getYear())
        .map(Book::getAuthor)
        .collect(Collectors.toSet());
    }

    public static List<Book> selectBooksByAuthor(List<Book> books, String author) {
        return books.stream()
        .filter(b -> author.equalsIgnoreCase(b.getAuthor()))
        .collect(Collectors.toList());
    }

    public static List<Book> selectBooksByYear(List<Book> books, int year) {
        return books.stream()
        .filter(b -> year == b.getYear())
        .collect(Collectors.toList());
    }

    public static List<Book> selectBooksByGenre(List<Book> books, String genre) {
        return books.stream()
        .filter(b -> genre.equalsIgnoreCase(b.getGenre()))
        .collect(Collectors.toList());
    }

    public static List<Book> removeBooksByAuthor(List<Book> books, String author) {
        books.removeIf(book -> book.getAuthor().equalsIgnoreCase(author));
        return books;
    }

    public static List<Book> sortByComparator (List<Book> books, Comparator<Book> comparator){
        books.sort(comparator);
        return books;
    }

    public static List<Book> mergeTwoCollections(List<Book> books1, List<Book> books2) {
        List<Book> merged = new ArrayList<>();
        merged.addAll(books1);
        merged.addAll(books2);
        return merged;
    }

    public static class BookCollectionMain {
        public static void main(String[] args) {
            //Create bookCollection
            ArrayList<Book> books = CreateBook.createBookCollection();

            //1. Print the list of all authors in the collection to the console.
            System.out.println("Authors: " + getBooksAuthors(books));

            //2. Print the list of authors who have written books in a given genre.
            String genre = "Adventure";
            System.out.println("\n" + genre + " genre Authors:");
            System.out.println(getBooksAuthorsByGenre(books, genre));


            //3. Print the list of authors whose books were published in a given year.
            int year = 2020;
            System.out.println("\nAuthors whose books were published in a " + year + ":");
            System.out.println(getBooksAuthorsByYear(books, year));

            //4. Find a book in the collection written by a given author.
            String author = "Jane Austen";

            //Create Books Collection to store selected books
            List<Book> newBooksCollection = new ArrayList<>();

            newBooksCollection = selectBooksByAuthor(books, author);
            System.out.println("\nBooks of " + author + ":");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }

            //5. Find all books that were written in a given year.
            newBooksCollection = selectBooksByYear(books, year);
            System.out.println("\nBooks published in a " + year + ":");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }

            //6. Find all books that belong to a given genre.
            newBooksCollection = selectBooksByGenre(books, genre);
            System.out.println("\n" + genre + " genre Books:");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }

            //7. Remove from the collection all books written by a given author.
            removeBooksByAuthor(books, author);
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
            sortByComparator(books,byTitle);
            //books.sort(byTitle);
            System.out.println("\nBooks collection sorted by Tittle:");
            for (Book book : books) {
                System.out.println(book);
            }

            //sort by Author
            sortByComparator(books,byAuthor);
            //books.sort(byAuthor);
            System.out.println("\nBooks collection sorted by Author:");
            for (Book book : books) {
                System.out.println(book);
            }

            //sort by Genre
            sortByComparator(books,byGenre);
            //books.sort(byGenre);
            System.out.println("\nBooks collection sorted by Genre:");
            for (Book book : books) {
                System.out.println(book);
            }

            //sort by Year
            sortByComparator(books,byYear);
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
            List<Book> mergedBookCollection = mergeTwoCollections(books, newCollection);

            System.out.println("\nBooks collection after merge with another Book Collection:");
            for (Book book : mergedBookCollection) {
                System.out.println(book);
            }

            //10. Create a subCollection of books from a given genre.
            newBooksCollection = selectBooksByGenre(mergedBookCollection, genre);
            System.out.println("\n" + genre + " genre Books:");
            for (Book book : newBooksCollection) {
                System.out.println(book);
            }
        }
    }
}
