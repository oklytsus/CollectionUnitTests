package org.softserve.book;

import java.util.ArrayList;

public class CreateBook {
    public static ArrayList<Book> createBookCollection() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("A Discovery of Witches", "Deborah Harkness", "Fantasy", 2011));
        books.add(new Book("Blood and Ash", "Jennifer L. Armentrout", "Adventure", 2020));
        books.add(new Book("Lady Susan", "Jane Austen", "Classic Literature", 1871));
        books.add(new Book("Sanditon", "Jane Austen", "Classic Literature", 1817));
        books.add(new Book("Mansfield Park", "Jane Austen", "Classic Literature", 1814));
        books.add(new Book("Rebel Island", "Rick Riordan", "Adventure", 2020));
        books.add(new Book("Mission Road", "Rick Riordan", "Adventure", 2005));
        books.add(new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "Fantasy", 2005));
        return books;
    }

    public static ArrayList<Book> createNewBookCollection() {
        ArrayList<Book> selectedBooks = new ArrayList<>();
        selectedBooks.add(new Book("New Moon", "Stephenie Meyer", "Fantasy", 2006));
        selectedBooks.add(new Book("Eclipse", "Stephenie Meyer", "Fantasy", 2007));
        return selectedBooks;
    }
}
