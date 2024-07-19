package org.example;

import lombok.RequiredArgsConstructor;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

@RequiredArgsConstructor
public class Main {
    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();

    public static void main( String[] args ) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookRepository bookRepository = (BookRepository) applicationContext.getBean("book");
        AuthorRepository authorRepository = (AuthorRepository) applicationContext.getBean("author");
//        Action action = applicationContext.getBean(Action.class);
        int choose;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("----- MENU -----");
            System.out.println("1. Create book");
            System.out.println("2. Create author");
            System.out.println("3. Display list book");
            System.out.println("4. Display list author");
            System.out.println("Choose case: ");
            choose = scanner.nextInt();

            switch (choose) {
                case 1:
                    createBook(scanner, bookRepository, authorRepository);
                    break;
                case 2:
                    createAuthor(scanner, authorRepository);
                    break;
                case 3:
                    displayBooks(bookRepository);
                    break;
                case 4:
                    displayAuthors(authorRepository);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choose > 0 && choose < 5);
    }


    public static void createBook(Scanner scanner, BookRepository bookRepository, AuthorRepository authorRepository) {
        Book book = new Book();
        int id;
        String title, authorName;
        boolean flag;
        System.out.println("----- Create book -----");
        do {
            System.out.println("Enter book id: ");
            id = scanner.nextInt();
            boolean idExists = false;
            for (Book item : books) {
                if (item.getId() == id) {
                    idExists = true;
                    System.out.println("ID already exists!");
                    break;
                }
            }
            flag = !idExists;
        } while (!flag);
        System.out.println("Enter book title: ");
        scanner.nextLine();
        title = scanner.nextLine();
        System.out.println("Enter author name: ");
        authorName = scanner.nextLine();
        Author author = authorRepository.getAllAuthor().stream().filter(item -> item.getName().equals(authorName)).findFirst().orElse(null);
        if (author != null) {
            book.setAuthor(author);
        } else {
            author = new Author(authorRepository.getAllAuthor().get(authors.size() - 1).getId()+1, authorName);
            authorRepository.saveAuthor(author);
            book.setAuthor(author);
        }
        book.setId(id);
        book.setTitle(title);
        bookRepository.saveBook(book);
        System.out.println("Create successfully");
    }

    public static void createAuthor(Scanner scanner, AuthorRepository authorRepository) {
        Author author = new Author();
        int id;
        String name;
        boolean flag;
        do {
            System.out.println("Enter author id: ");
            id = scanner.nextInt();
            boolean idExists = false;
            for (Author item : authors) {
                if (item.getId() == id) {
                    idExists = true;
                    System.out.println("ID already exists!");
                    break;
                }
            }
            flag = !idExists;
        } while (!flag);
        System.out.println("Enter author name: ");
        scanner.nextLine();
        name = scanner.nextLine();
        author.setId(id);
        author.setName(name);
        authorRepository.saveAuthor(author);
        System.out.println("Create successfully");
    }

    public static void displayBooks(BookRepository bookRepository) {
        List<Book> books = bookRepository.getAllBook();
        books.stream().forEach(System.out::println);
    }

    public static void displayAuthors(AuthorRepository authorRepository) {
        List<Author> authors = authorRepository.getAllAuthor();
        authors.stream().forEach(System.out::println);
    }

    static {
        authors.add(new Author(1, "Nguyễn Thu Hà"));
        authors.add(new Author(2, "Nguyễn Văn An"));
        authors.add(new Author(3, "Lê Văn Nam"));
        authors.add(new Author(4, "Trần Ngọc"));
        authors.add(new Author(5, "Nguyễn Thị Ánh"));
    }
    static {
        Author book1 = authors.get(0);
        books.add(new Book(1, "Tiếng Việt 1", book1));

        Author book2 = authors.get(1);
        books.add(new Book(2, "Tiếng Việt 2", book2));
    }
}
