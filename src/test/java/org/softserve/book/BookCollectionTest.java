package org.softserve.book;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BookCollectionTest {
    private BookCollection bookCollection;
    private ArrayList<Book> testBooks;
    private Set<String> expectedSetCollection;
    private Set<String> actualSetCollection;
    private ArrayList<Book> expectedBookCollection;
    private ArrayList<Book> actualCollection;

    @BeforeAll
    static public void setUpBeforeAll() {
        System.out.println("Setting up resources for the test");
    }

    @BeforeEach
    void setUp() {
        bookCollection = new BookCollection();
        expectedSetCollection = new HashSet<>();
        actualSetCollection = new HashSet<>();
        expectedBookCollection = new ArrayList<>();
        actualCollection = new ArrayList<>();
        testBooks = CreateBook.createBookCollection();
    }

    @AfterEach
    void tearDown() {
        bookCollection = null;
        testBooks.clear();
        expectedSetCollection.clear();
        expectedBookCollection.clear();
        actualCollection.clear();
        actualSetCollection.clear();
    }

    @Test
    void testTotalNumberOfBooks() {
        assertEquals(8, testBooks.size(), "Total number of books should be 8");
    }

    @Test
    void testAuthorCollectionsMatch() {
        List<String> list = List.of("Deborah Harkness", "Jennifer L. Armentrout", "Jane Austen", "Rick Riordan", "J. K. Rowling");
        expectedSetCollection.addAll(list);
        actualSetCollection = bookCollection.getBooksAuthors(testBooks);
        assertEquals(expectedSetCollection, actualSetCollection, "Collections should match");
    }

    @ParameterizedTest
    @MethodSource("listAndGenreProvider")
    void testSetCollectionContentSelectedByGenre(String genre, List<String> list) {
        expectedSetCollection.addAll(list);
        actualSetCollection = bookCollection.getBooksAuthorsByGenre(testBooks, genre);
        assertEquals(expectedSetCollection, actualSetCollection, "Collections should match");
    }

    @Test
    void testSetCollectionIsEmptyForIncorrectGenre() {
        actualSetCollection = bookCollection.getBooksAuthorsByGenre(testBooks, "Romans");
        assertTrue(actualSetCollection.isEmpty(), "Collections should be empty");
    }

    @ParameterizedTest
    @MethodSource("listAndYearProvider")
    void testSetCollectionContentSelectedByYear(int year, List<String> list) {
        expectedSetCollection.addAll(list);
        actualSetCollection = bookCollection.getBooksAuthorsByYear(testBooks, year);
        assertEquals(expectedSetCollection, actualSetCollection, "Collections should match");
    }

    @Test
    void testSetCollectionIsEmptyForIncorrectYear() {
        actualSetCollection = bookCollection.getBooksAuthorsByYear(testBooks, 2000);
        assertTrue(actualSetCollection.isEmpty(), "Collections should be empty");
    }

    @ParameterizedTest
    @MethodSource("objectAndAuthorProvider")
    void testCollectionContentSelectedByAuthor(String author, ArrayList<Book> expectedBookCollection) {
        actualCollection = bookCollection.selectBooksByAuthor(testBooks, author);
        assertEquals(expectedBookCollection, actualCollection, "Collections should match");
    }

    @Test
    void testCollectionIsEmptyForIncorrectAuthor() {
        actualCollection = bookCollection.selectBooksByAuthor(testBooks, "Jane Marfy");
        assertTrue(actualCollection.isEmpty(), "Collections should be empty");
    }

    @ParameterizedTest
    @MethodSource("objectAndYearProvider")
    void testCollectionContentSelectedByYear(int year, ArrayList<Book> expectedBookCollection) {
        actualCollection = bookCollection.selectBooksByYear(testBooks, year);
        assertEquals(expectedBookCollection, actualCollection, "Collections should match");
    }

    @Test
    void testCollectionIsEmptyForIncorrectYear() {
        actualCollection = bookCollection.selectBooksByYear(testBooks, 2000);
        assertTrue(actualCollection.isEmpty(), "Collections should be empty");
    }

    @ParameterizedTest
    @MethodSource("objectAndGenreProvider")
    void testCollectionContentSelectedByGenre(String genre, ArrayList<Book> expectedBookCollection) {
        actualCollection = bookCollection.selectBooksByGenre(testBooks, genre);
        assertEquals(expectedBookCollection, actualCollection, "Collections should match");
    }

    @Test
    void testCollectionIsEmptyForIncorrectGenre() {
        actualCollection = bookCollection.selectBooksByGenre(testBooks, "Romans");
        assertTrue(actualCollection.isEmpty(), "Collections should be empty");
    }

    @ParameterizedTest
    @MethodSource("objectAndParameterProvider")
    void testCollectionContentAfterRemovalByAuthor(String author, ArrayList<Book> expectedBookCollection) {
        actualCollection = bookCollection.removeBooksByAuthor(testBooks, author);
        assertEquals(expectedBookCollection, actualCollection, "Collections should match");
    }

    @ParameterizedTest
    @MethodSource("ComparatorProvider")
    void testSortedCollectionContent(String str, Comparator<Book> comparator) {
        testBooks.sort(comparator);
        actualCollection = bookCollection.sortByComparator(testBooks, comparator);
        assertEquals(testBooks, actualCollection, "Collections should match");
    }

    @Test
    void testTotalNumbersOfNewBooks() {
        actualCollection = CreateBook.createNewBookCollection();
        assertEquals(2, actualCollection.size(), "Total number of books should be 2");
    }

    @Test
    void testTotalNumbersOfMergedCollection() {
        actualCollection = CreateBook.createNewBookCollection();
        actualCollection = bookCollection.mergeTwoCollections(testBooks, actualCollection);
        assertEquals(10, actualCollection.size(), "Total number of books should be 10");
    }

    @Test
    void testMergedCollectionContent() {
        expectedBookCollection = CreateBook.createBookCollection();
        actualCollection = CreateBook.createNewBookCollection();
        expectedBookCollection.addAll(actualCollection);
        actualCollection = bookCollection.mergeTwoCollections(testBooks, actualCollection);
        assertEquals(expectedBookCollection, actualCollection, "Collections should match");
    }

    @AfterAll
    static public void tearDownAfterAll() {
        System.out.println("Cleaning up resources after the test");
    }

    private static Object[][] listAndGenreProvider() {
        return new Object[][]{
                {"Fantasy", List.of("Deborah Harkness", "J. K. Rowling")},
                {"Adventure", List.of("Jennifer L. Armentrout", "Rick Riordan")}
        };
    }

    private static Object[][] listAndYearProvider() {
        return new Object[][]{
                {2020, List.of("Jennifer L. Armentrout", "Rick Riordan")},
                {2005, List.of("Rick Riordan", "J. K. Rowling")},
                {2011, List.of("Deborah Harkness")}
        };
    }

    private static Object[][] objectAndAuthorProvider() {
        ArrayList<Book> testBooks;
        testBooks = CreateBook.createBookCollection();

        ArrayList<Book> expectedBookCollection1 = new ArrayList<>();
        for (Book book : testBooks) {
            if (book.getAuthor().equalsIgnoreCase("Jane Austen")) {
                expectedBookCollection1.add(book);
            }
        }

        ArrayList<Book> expectedBookCollection2 = new ArrayList<>();
        for (Book book : testBooks) {
            if (book.getAuthor().equalsIgnoreCase("Rick Riordan")) {
                expectedBookCollection2.add(book);
            }
        }

        return new Object[][]{
                {"Jane Austen", expectedBookCollection1},
                {"Rick Riordan", expectedBookCollection2}
        };
    }

    private static Object[][] objectAndYearProvider() {
        ArrayList<Book> testBooks;
        testBooks = CreateBook.createBookCollection();

        ArrayList<Book> expectedBookCollection1 = new ArrayList<>();
        for (Book book : testBooks) {
            if (book.getYear() == 2020) {
                expectedBookCollection1.add(book);
            }
        }

        ArrayList<Book> expectedBookCollection2 = new ArrayList<>();
        for (Book book : testBooks) {
            if (book.getYear() == 2005) {
                expectedBookCollection2.add(book);
            }
        }

        return new Object[][]{
                {2020, expectedBookCollection1},
                {2005, expectedBookCollection2}
        };
    }

    private static Object[][] objectAndGenreProvider() {
        ArrayList<Book> testBooks;
        testBooks = CreateBook.createBookCollection();

        ArrayList<Book> expectedBookCollection1 = new ArrayList<>();
        for (Book book : testBooks) {
            if (book.getGenre().equalsIgnoreCase("Fantasy")) {
                expectedBookCollection1.add(book);
            }
        }

        ArrayList<Book> expectedBookCollection2 = new ArrayList<>();
        for (Book book : testBooks) {
            if (book.getGenre().equalsIgnoreCase("Adventure")) {
                expectedBookCollection2.add(book);
            }
        }

        return new Object[][]{
                {"Fantasy", expectedBookCollection1},
                {"Adventure", expectedBookCollection2}
        };
    }

    private static Object[][] objectAndParameterProvider() {
        ArrayList<Book> expectedBookCollection1;
        expectedBookCollection1 = CreateBook.createBookCollection();
        expectedBookCollection1.removeIf(book -> book.getAuthor().equalsIgnoreCase("Jane Austen"));

        ArrayList<Book> expectedBookCollection2;
        expectedBookCollection2 = CreateBook.createBookCollection();
        expectedBookCollection2.removeIf(book -> book.getAuthor().equalsIgnoreCase("Rick Riordan"));

        return new Object[][]{
                {"Jane Austen", expectedBookCollection1},
                {"Rick Riordan", expectedBookCollection2}
        };
    }

    private static Object[] ComparatorProvider() {
        Comparator<Book> byTitle = Comparator.comparing(Book::getTitle);
        Comparator<Book> byAuthor = Comparator.comparing(Book::getAuthor);
        Comparator<Book> byGenre = Comparator.comparing(Book::getGenre);
        Comparator<Book> byYear = Comparator.comparing(Book::getYear);
        return new Object[][]{
                {"By Title", byTitle},
                {"By Author", byAuthor},
                {"By Genre", byGenre},
                {"By Year", byYear}
        };
    }

}