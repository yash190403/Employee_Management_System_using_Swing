package DaoFile;

import Connection.GetConnection;
import pck1.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Addempdao {

    public void addEmployee(Employee e) {
        String sql = "INSERT INTO employees(name, email, phone, department, position, salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getPhone());
            ps.setString(4, e.getDepartment());
            ps.setString(5, e.getPosition());
            ps.setDouble(6, e.getSalary());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = GetConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("department"),
                    rs.getString("position"),
                    rs.getDouble("salary")
                );
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void updateEmployee(Employee e) {
        String sql = "UPDATE employees SET name=?, email=?, phone=?, department=?, position=?, salary=? WHERE id=?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getPhone());
            ps.setString(4, e.getDepartment());
            ps.setString(5, e.getPosition());
            ps.setDouble(6, e.getSalary());
            ps.setInt(7, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = GetConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

