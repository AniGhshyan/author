package author.manager;

import author.db.DBConnectionProvider;
import author.model.User;

import java.sql.*;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(User user) {
        String sql = String.format("insert into user(name,surname,email,password,user_type) VALUES('%s','%s','%s','%s','%s')", user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getType().name());
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet=statement.getGeneratedKeys();
            if (resultSet.next()){
                int anInt = resultSet.getInt(0);
                user.setId(anInt);
            }
            System.out.println("User was added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
