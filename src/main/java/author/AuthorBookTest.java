package author;

import author.exception.BookNotFoundException;
import author.manager.AuthorManager;
import author.manager.BookManager;
import author.manager.UserManager;
import author.model.*;
import author.util.DateUtil;

import java.text.ParseException;
import java.util.*;

public class AuthorBookTest implements AuthorBookCommands {
    static Scanner scanner = new Scanner(System.in);
    static UserManager userManager = new UserManager();
    static AuthorManager authorManager = new AuthorManager();
    static BookManager bookManager = new BookManager();


    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            AuthorBookCommands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void login() {
        System.out.println("Please input email");
        String email = scanner.nextLine();
        User user = userManager.getByEmail(email);
        if (user != null) {
            System.out.println("Please input user's password");
            String password = scanner.nextLine();
            if (user.getPassword().equals(password)) {
                if (user.getType() == UserType.ADMIN) {
                    adminLogin();
                } else if (user.getType() == UserType.USER) {
                    userLogin();
                }
            } else {
                System.err.println("Password is wrong!!");
            }
        } else {
            System.err.println("user with " + email + "does not exists");
        }
    }

    private static void userLogin() {
        boolean isRun = true;
        while (isRun) {
            AuthorBookCommands.printUserCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    System.exit(0);
                    break;
                case ADD_AUTHORS:
                    addAuthor();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case SEARCH_AUTHORS:
                    searchByName();
                    break;
                case SEARCH_AUTHORS_BY_AGE:
                    searchByAge();
                    break;
                case SEARCH_BOOKS_BY_TITLE:
                    searchBooksBYTitle();
                    break;
                case PRINT_AUTHORS:
                    authorManager.print();
                    break;
                case PRINT_BOOKS:
                    bookManager.print();
                    break;
                case SEARCH_BOOKS_BY_AUTHOR:
                    searchBooksByAuthor();
                    break;
                case COUNT_BOOKS_BY_AUTHOR:
                    countBookByAuthor();
                    break;
                case LOGOUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void register() {
        System.out.println("Please input email");
        String email = scanner.nextLine();
        User byEmail = userManager.getByEmail(email);
        if (byEmail == null) {
            System.out.println("Please input user's name");
            String name = scanner.nextLine();

            System.out.println("Please input user's surname");
            String surname = scanner.nextLine();

            System.out.println("Please input user's password");
            String password = scanner.nextLine();

            System.out.println("Please input user's type(ADMIN or USER");
            String type = scanner.nextLine();
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setPassword(password);
            user.setType(UserType.valueOf(type.toUpperCase()));
            userManager.add(user);
            System.out.println("User was registered");
        } else {
            System.err.println("user with " + email + "already exists");
        }
    }

    private static void adminLogin() {
        boolean isRun = true;
        while (isRun) {
            AuthorBookCommands.printAdminCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    System.exit(0);
                    break;
                case ADD_AUTHORS:
                    addAuthor();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case SEARCH_AUTHORS:
                    searchByName();
                    break;
                case SEARCH_AUTHORS_BY_AGE:
                    searchByAge();
                    break;
                case SEARCH_BOOKS_BY_TITLE:
                    searchBooksBYTitle();
                    break;
                case PRINT_AUTHORS:
                    authorManager.print();
                    break;
                case PRINT_BOOKS:
                    bookManager.print();
                    break;
                case SEARCH_BOOKS_BY_AUTHOR:
                    searchBooksByAuthor();
                    break;
                case COUNT_BOOKS_BY_AUTHOR:
                    countBookByAuthor();
                    break;
                case CHANGE_AUTHOR:
                    changeAuthor();
                    break;
                case CHANGE_BOOK_AUTHOR:
                    changeBookAuthor();
                    break;
                case DELETE_BY_AUTHOR:
                    deleteByAuthor();
                    break;
                case DELETE_AUTHOR:
                    deleteAuthor();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
//                case ADD_TAG_TO_BOOK:
//                    addTagsToBook();
//                    break;
//                case REMOVE_TAGS_FROM_BOOK:
//                    removeTagsFromBook();
//                    break;
                case LOGOUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }


//    private static void removeTagsFromBook() {
//        System.out.println("Please choose book's serialId");
//        System.out.println("-----");
//        bookStorage.print();
//        System.out.println("-----");
//        String serialId = scanner.nextLine();
//        Book book = null;
//        try {
//            book = bookStorage.getBySerialId(serialId);
//            System.out.println("PLease input tags separate ,");
//            String tagsStr = scanner.nextLine();
//            String[] tagsToRemove = tagsStr.split(",");
//            Set<String> bookTags = book.getTags();
//            if (bookTags == null) {
//                System.err.println("Book does not have any tags to remove!!!!");
//            } else {
//                bookTags.removeAll(Arrays.asList(tagsToRemove));
//            }
//        } catch (BookNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private static void addTagsToBook() {
//        System.out.println("Please choose book's serialId");
//        System.out.println("-----");
//        bookStorage.print();
//        System.out.println("-----");
//        String serialId = scanner.nextLine();
//        Book book;
//        try {
//            book = bookStorage.getBySerialId(serialId);
//            System.out.println("PLease input tags separate ,");
//            String tagsStr = scanner.nextLine();
//            String[] tags = tagsStr.split(",");
//            Set<String> bookTags = book.getTags();
//            if (bookTags == null) {
//                book.setTags(new HashSet<>(Arrays.asList(tags)));
//                System.out.println("Tags were added!!!");
//            } else {
//                bookTags.addAll(Arrays.asList(tags));
//                System.out.println("Tags were added!!!");
//            }
//        } catch (BookNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//    }
//

    private static void deleteBook() {
        System.out.println("Please choose book by serial id");
        System.out.println("-------");
        bookManager.print();
        System.out.println("-------");
        long id = scanner.nextLong();
        Book book = bookManager.getById(id);
        if (book != null) {
            bookManager.delete(id);
        }
    }

    private static void deleteByAuthor() {
        printAuthorsList();
        String email = scanner.nextLine();
        Author author = authorManager.getByEmail(email);
        if (author != null) {
            bookManager.deleteByAuthor(author);
        } else {
            System.out.println("Author does not exists");
        }
    }

    private static void printAuthorsList() {
        System.out.println("please choose author's email separate ,");
        System.out.println("--------");
        authorManager.print();
        System.out.println("--------");

    }

    private static void deleteAuthor() {
        printAuthorsList();
        String email = scanner.nextLine();
        Author author = authorManager.getByEmail(email);
        if (author != null) {
            authorManager.delete(author);
        } else {
            System.err.println("Author does not exists");
        }
    }

    private static void changeBookAuthor() {
        System.out.println("Please choose book's serialId");
        System.out.println("-----");
        bookManager.print();
        System.out.println("-----");
        long id = scanner.nextLong();
        Book book;

        book = bookManager.getById(id);
        printAuthorsList();

        String emails = scanner.nextLine();
        String[] emailArray = emails.split(",");
        if (emailArray.length == 0) {
            System.err.println("Please choose authors");
            return;
        }
        Set<Author> authors = new HashSet<>();
        for (String email : emailArray) {
            Author author = authorManager.getByEmail(email);
            if (author != null) {
                authors.add(author);
            } else {
                System.err.println("Please input correct author's email ");
                return;
            }
        }
        book.setAuthors(authors);
//            bookStorage.serialize();
    }

    private static void changeAuthor() {
        printAuthorsList();
        String email = scanner.nextLine();
        Author author = authorManager.getByEmail(email);
        if (author != null) {
            System.out.println(author);
            System.out.println("please input author's name: ");
            String name = scanner.nextLine();
            System.out.println("please input author's surname: ");
            String surname = scanner.nextLine();
            System.out.println("please input author's gender: ");
            try {
                Gender gender = Gender.valueOf(scanner.nextLine());
                System.out.println("please input author's age: ");
                int age = Integer.parseInt(scanner.nextLine());
                author.setAge(age);
                author.setName(name);
                author.setSurname(surname);
                author.setGender(gender);
//                authorStorage.serialize();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.err.println("Author does not exists");
        }
    }

    private static void countBookByAuthor() {
        printAuthorsList();
        String email = scanner.nextLine();
        Author author = authorManager.getByEmail(email);
        if (author != null) {
            bookManager.countByAuthor(author);
        } else {
            System.out.println("Author does not exists");
        }

    }

    private static void searchBooksByAuthor() {
        printAuthorsList();
        String email = scanner.nextLine();
        Author author = authorManager.getByEmail(email);
        if (author != null) {
            bookManager.searchByAuthor(author);
        } else {
            System.out.println("Author does not exists");
        }
    }

    private static void searchBooksBYTitle() {
        System.out.println("Please input keyword");
        String keyword = scanner.nextLine();
        bookManager.searchBooksByTitle(keyword);
    }

    private static void addBook() {
        printAuthorsList();
        String emails = scanner.nextLine();
        String[] emailArray = emails.split(",");
        if (emailArray.length == 0) {
            System.err.println("Please choose authors");
            return;
        }
        Set<Author> authors = new HashSet<>();
        for (String email : emailArray) {
            Author author = authorManager.getByEmail(email);
            if (author != null) {
                authors.add(author);
            } else {
                System.err.println("Please input correct author's email ");
                return;
            }
        }
        System.out.println("please input book's Id: ");
        Long id = scanner.nextLong();
        if (bookManager.getById(id) == null) {
            System.out.println("please input book's title: ");
            String title = scanner.nextLine();
            System.out.println("please input book's description: ");
            String description = scanner.nextLine();
            System.out.println("please input book's price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.println("please input book's count: ");
            int count = Integer.parseInt(scanner.nextLine());
            Book book = new Book(id, title, description, price, count, authors);
            bookManager.add(book);
            System.out.println("Thank you,book was added");
        } else {
            System.out.println("Book is duplicate ");
        }

    }
//        try {
//            bookManager.getById(Id);
//            System.out.println("Book is duplicate ");
//        } catch (BookNotFoundException e) {
//            System.out.println("please input book's title: ");
//            String title = scanner.nextLine();
//            System.out.println("please input book's description: ");
//            String description = scanner.nextLine();
//            System.out.println("please input book's price: ");
//            double price = Double.parseDouble(scanner.nextLine());
//            System.out.println("please input book's count: ");
//            int count = Integer.parseInt(scanner.nextLine());
//            System.out.println("Please input book's tags separate ,");
//            String tagsSrt = scanner.nextLine();
//            String[] tags = tagsSrt.split(",");


    private static void searchByAge() {
        System.out.println("please input min age");
        int minAge = Integer.parseInt(scanner.nextLine());
        System.out.println("please input max age");
        int maxAge = Integer.parseInt(scanner.nextLine());
        authorManager.searchByAge(minAge, maxAge);
    }

    private static void searchByName() {
        System.out.println("Please input keyword");
        String keyword = scanner.nextLine();
        authorManager.searchByName(keyword);
    }

    private static void addAuthor() {
        System.out.println("please input author's name,surname,email,gender,age,dateOfBirth[12/12/2021]");
        String authorDataStr = scanner.nextLine();
        String[] authorData = authorDataStr.split(",");
        if (authorData.length == 6) {
            int age = Integer.parseInt(authorData[4]);
            Date date;
            try {
                date = DateUtil.stringToDate(authorData[5]);
            } catch (ParseException e) {
                System.out.println("invalid date format");
                return;
            }
            Author author = new Author();
            author.setName(authorData[0]);
            author.setSurname(authorData[1]);
            author.setEmail(authorData[2]);
            author.setAge(age);
            author.setGender(Gender.valueOf(authorData[3]));

            if (authorManager.getByEmail(author.getEmail()) != null) {
                System.err.println("Invalid email.Author with this email already exists");
            } else {
                authorManager.add(author);
                System.out.println("Thank you, author was added");
            }
        } else {
            System.err.println("invalid data");
        }
    }
}





