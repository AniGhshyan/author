package author.manager;

import author.db.DBConnectionProvider;
import author.model.Author;
import author.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    AuthorManager authorManager = new AuthorManager();
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(Book book) {
        String sql = String.format("insert into user(title,description,price,count,author_id) VALUES('%s','%s','%s','%s','%s')",
                book.getTitle(), book.getDescription(), book.getPrice(), book.getCount(), book.getAuthors());
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int anInt = resultSet.getInt(0);
                book.setId(anInt);
            }
            System.out.println("Book was added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) {

        try {
            return Book.builder()
                    .id(resultSet.getLong(1))
                    .title(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .price(resultSet.getDouble(4))
                    .count(resultSet.getInt(5))
                    .authors(authorManager.getAuthorsById(resultSet.getLong(6)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void print() {
        List<Book> books = getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book getById(long id) {
        String sql = "SELECT * FROM book WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book searchBooksByTitle(String keyword) {
        String sql = "SELECT * FROM book WHERE title = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, keyword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Book searchByAuthor(Author author) {
        String sql = "SELECT * FROM book WHERE authors_id = ?" + author.getId();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countByAuthor(Author author) {
        int count = 0;
        String sql = "SELECT count FROM book WHERE authors_id = ?" + author.getId();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                count = resultSet.getInt(5);
                count += count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean deleteByAuthor(Author author) {
        String sql = "UPDATE book Where author_id = " + author.getId();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM book WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

