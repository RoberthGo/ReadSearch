package com.personal.project.readsearch.main;

import com.personal.project.readsearch.models.Book;
import com.personal.project.readsearch.models.BookData;
import com.personal.project.readsearch.models.Data;
import com.personal.project.readsearch.repository.IAuthorRepository;
import com.personal.project.readsearch.repository.IBookRepository;
import com.personal.project.readsearch.service.InputValidation;
import com.personal.project.readsearch.service.ApiConnection;
import com.personal.project.readsearch.service.ConvertDataInJson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private InputValidation input = new InputValidation();
    private ApiConnection connection = new ApiConnection();
    private ConvertDataInJson convertData = new ConvertDataInJson();
    private IAuthorRepository authorRepository;
    private IBookRepository bookRepository;
    private final List<String> validLanguage = List.of(new String[]{"en", "fr", "de", "es", "it", "pt", "nl", "fi", "la", "hu", "da", "no", "sv", "pl", "el", "zh", "ru", "ja", "ca"});

    public Menu(IBookRepository bookRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        String ans = "";
        System.out.println("""
                |***************************************************|
                |*****         Welcome to ReadSearch!         ******|
                |***************************************************|
                """);
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Search book by title");
            System.out.println("2. List registered books");
            System.out.println("3. List registered authors");
            System.out.println("4. List of living authors between certain years ");
            System.out.println("5. List of books by language");
            System.out.println("6. Top 10 most downloaded books in the Api");
            System.out.println("7. Top 10 most downloaded registered books");
            System.out.println("8. Exit");
            ans = input.nextString();

            switch (ans) {
                case "1":
                    saveSearchInDataBase();
                    break;
                case "2":
                    listRegisteredBooks();
                    break;
                case "3":
                    listRegisteredAuthors();
                    break;
                case "4":
                    listLivingAuthors();
                    break;
                case "5":
                    listBooksByLanguage();
                    break;
                case "6":
                    showTop10();
                    break;
                case "7":
                    showTop10Registered();
                    break;
                default:
                    if (!ans.equals("8")) System.out.println("Invalid option. Please try again.");
                    break;
            }
            System.out.println("Press enter to continue....");
            input.nextString();
        } while (!ans.equals("8"));
        System.out.println("Goodbye!");
    }

    void saveSearchInDataBase() {
        List<Book> books = searchBookByTitle();
        if (books.size() == 0) {
            System.out.println("The book could not be found! :(");
            return;
        }
        if (!bookRepository.existsByTitle(books.get(0).getTitle())) {
            bookRepository.save(books.get(0));
            System.out.println("Book successfully registered in the database ");
        } else {
            System.out.println("The book is already registered");
        }
        System.out.println("\n" + books.get(0));
    }

    List<Book> searchBookByTitle() {
        System.out.println("Enter the title of the book you want to search:");
        String title = input.nextString();
        String json = connection.request("search=", title);
        List<Book> books = convertData.getData(json, Data.class)
                .books()
                .stream()
                .filter(e -> e.title().toUpperCase().contains(title.toUpperCase()))
                .map(e -> new Book(e))
                .collect(Collectors.toList());
        return books;
    }


    void listRegisteredBooks() {
        System.out.println("The registered books are:\n");
        printList(bookRepository.findAll());
    }

    void listRegisteredAuthors() {
        System.out.println("The registered Authors are:\n");
        printList(authorRepository.findAll());
    }

    void listLivingAuthors() {
        int birthYear = input.nextInt("Write the year of birth: ");
        int deadYear = input.nextInt("Write the year of death: ");
        while (deadYear < birthYear) {
            System.out.println("The year of death must be later than the year of birth");
            birthYear = input.nextInt("Write the year of birth: ");
            deadYear = input.nextInt("Write the year of death: ");
        }
        System.out.println("The living authors are:");
        printList(authorRepository.findAuthorByBirthYearGreaterThanEqualAndBirthYearLessThanEqual(birthYear, deadYear));
    }

    void listBooksByLanguage() {
        System.out.println("Choose a language: ");
        System.out.println("Please use the 2-letter format, for example:");
        System.out.println("en - English");
        System.out.println("es - Spanish");
        System.out.println("fr - French");
        System.out.println("pt - Portuguese");
        String language = input.nextString();

        while (!validLanguage.contains(language.toLowerCase())) {
            System.out.println("Enter a valid language or type 0 if you want to return to the menu");
            language = input.nextString();
            if (language.equals("0")) {
                return;
            }
        }

        List<Book> books = bookRepository.findByLanguagesIsLikeIgnoreCase("%" + language + "%");
        if (books.size() > 0) {
            System.out.println(books.size() + " record books were found in the language " + language);
        }
        printList(books);
    }

    void showTop10() {
        String json = connection.request("","");
        List<Book> books = convertData.getData(json, Data.class)
                .books()
                .stream()
                .map(e -> new Book(e))
                .collect(Collectors.toList());
        printList(SortTop10(books));
    }

    void showTop10Registered(){
        printList(SortTop10(bookRepository.findAll()));
    }

    List<Book> SortTop10(List<Book> books) {
        System.out.println("Top 10:\n-------------------\n");
        return books.stream()
                .sorted(Comparator.comparing(Book::getDownload_count).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public <T> void printList(List<T> entity) {
        if (entity.size() == 0) {
            System.out.println("No records are available!");
        } else {
            entity.stream().forEach(e -> System.out.println(e));
        }
    }
}