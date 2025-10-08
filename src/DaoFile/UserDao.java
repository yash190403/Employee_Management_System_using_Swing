package DaoFile;

import Connection.GetConnection;
import pck1.User;
import java.sql.*;

public class UserDao {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists!");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if match found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
