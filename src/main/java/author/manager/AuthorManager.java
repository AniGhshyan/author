package author.manager;

import author.db.DBConnectionProvider;
import author.model.Author;
import author.model.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void print() {
        List<Author> authors = getAllAuthors();
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    public void add(Author author) {
        String sql = String.format("insert into user(name,surname,email,age,gender,date_of_birth) VALUES('%s','%s','%s','%s','%s','%s)",
                author.getName(), author.getSurname(), author.getEmail(), author.getAge(), author.getGender().name(), author.getDateOfBirth());
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int anInt = resultSet.getInt(0);
                author.setId(anInt);
            }
            System.out.println("Author was added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Author getByEmail(String email) {
        String sql = "SELECT * FROM author WHERE email = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) {
        try {
            return Author.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .age(resultSet.getInt(5))
                    .gender(Gender.valueOf(resultSet.getString(6)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM author";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Set<Author> getAuthorsById(long id) {
        Set<Author> authors = new HashSet<>();
        String sql = "SELECT * FROM author WHERE id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Author searchByName(String keyword) {
        String sql = "SELECT * FROM author Where name =?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, keyword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Set<Author> searchByAge(int minAge, int maxAge) {
        Set<Author> authors = new HashSet<>();

        String sql = String.format("SELECT * FROM author WHERE age BETWEEN ? AND ?", minAge, maxAge);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    public boolean delete(Author author) {
        String sql = "DELETE FROM author WHERE id = " + author.getId();
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

