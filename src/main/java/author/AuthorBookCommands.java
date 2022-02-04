package author;

public interface AuthorBookCommands {
    String EXIT = "0";
    String ADD_AUTHORS = "1";
    String ADD_BOOK = "2";
    String SEARCH_AUTHORS = "3";
    String SEARCH_AUTHORS_BY_AGE = "4";
    String SEARCH_BOOKS_BY_TITLE = "5";
    String PRINT_AUTHORS = "6";
    String PRINT_BOOKS = "7";
    String SEARCH_BOOKS_BY_AUTHOR = "8";
    String COUNT_BOOKS_BY_AUTHOR = "9";
    String CHANGE_AUTHOR = "10";
    String CHANGE_BOOK_AUTHOR = "11";
    String DELETE_BY_AUTHOR = "12";
    String DELETE_AUTHOR = "13";
    String DELETE_BOOK = "14";
    String ADD_TAG_TO_BOOK = "15";
    String REMOVE_TAGS_FROM_BOOK = "16";

    // user commands
    String LOGOUT = "17";
    String LOGIN = "1";
    String REGISTER = "2";

    static void printCommands() {
        System.out.println("\u001B[34m" + "please input " + EXIT + " for EXIT");
        System.out.println("please input " + LOGIN + " for LOGIN ");
        System.out.println("please input " + REGISTER + " for REGISTER " + "\u001B[0m");
    }

    static void printAdminCommands() {
        System.out.println("\u001B[34m" + "please input " + EXIT + " for EXIT");
        System.out.println("please input " + ADD_AUTHORS + " for author");
        System.out.println("please input " + ADD_BOOK + " for book");
        System.out.println("please input " + SEARCH_AUTHORS + " for search author by name");
        System.out.println("please input " + SEARCH_AUTHORS_BY_AGE + " for search author by age");
        System.out.println("please input " + SEARCH_BOOKS_BY_TITLE + " for search book by title");
        System.out.println("please input " + PRINT_AUTHORS + " for print authors");
        System.out.println("please input " + PRINT_BOOKS + " for print books");
        System.out.println("please input " + SEARCH_BOOKS_BY_AUTHOR + " for search book by authors");
        System.out.println("please input " + COUNT_BOOKS_BY_AUTHOR + " for count book by authors");
        System.out.println("please input " + CHANGE_AUTHOR + " for change author's information");
        System.out.println("please input " + CHANGE_BOOK_AUTHOR + " for change author");
        System.out.println("please input " + DELETE_BY_AUTHOR + " for delete book by author");
        System.out.println("please input " + DELETE_AUTHOR + " for delete author");
        System.out.println("please input " + DELETE_BOOK + " for delete book");
        System.out.println("please input " + ADD_TAG_TO_BOOK + " for add tag to book");
        System.out.println("please input " + REMOVE_TAGS_FROM_BOOK + " for remove tag from book");
        System.out.println("please input " + LOGOUT + " for logout" + "\u001B[0m");
    }

    static void printUserCommands() {
        System.out.println("\u001B[34m" + "please input " + EXIT + " for EXIT");
        System.out.println("please input " + ADD_AUTHORS + " for author");
        System.out.println("please input " + ADD_BOOK + " for book");
        System.out.println("please input " + SEARCH_AUTHORS + " for search author by name");
        System.out.println("please input " + SEARCH_AUTHORS_BY_AGE + " for search author by age");
        System.out.println("please input " + SEARCH_BOOKS_BY_TITLE + " for search book by title");
        System.out.println("please input " + PRINT_AUTHORS + " for print authors");
        System.out.println("please input " + PRINT_BOOKS + " for print books");
        System.out.println("please input " + SEARCH_BOOKS_BY_AUTHOR + " for search book by authors");
        System.out.println("please input " + COUNT_BOOKS_BY_AUTHOR + " for count book by authors");
        System.out.println("please input " + LOGOUT + " for logout" + "\u001B[0m");
    }

}
