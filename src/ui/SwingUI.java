package ui;
import DaoFile.Addempdao;
import pck1.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SwingUI extends JFrame {
    private JTextField nameField, emailField, phoneField, deptField, posField, salaryField;
    private JTable table;
    private DefaultTableModel model;
    private Addempdao dao = new Addempdao();
    private int selectedId = -1;

    public SwingUI() {
        setTitle("Employee Management System");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Form ---
        JPanel form = new JPanel(new GridLayout(3, 4, 10, 10));
        nameField = new JTextField(); emailField = new JTextField();
        phoneField = new JTextField(); deptField = new JTextField();
        posField = new JTextField(); salaryField = new JTextField();
        form.add(new JLabel("Name")); form.add(nameField);
        form.add(new JLabel("Email")); form.add(emailField);
        form.add(new JLabel("Phone")); form.add(phoneField);
        form.add(new JLabel("Department")); form.add(deptField);
        form.add(new JLabel("Position")); form.add(posField);
        form.add(new JLabel("Salary")); form.add(salaryField);
        add(form, BorderLayout.NORTH);

        // --- Buttons ---
        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        add(buttons, BorderLayout.SOUTH);

        // --- Table ---
        model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Department", "Position", "Salary"}, 0);
        table = new JTable(model);
        loadTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Listeners ---
        addBtn.addActionListener(e -> addEmployee());
        updateBtn.addActionListener(e -> updateEmployee());
        deleteBtn.addActionListener(e -> deleteEmployee());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                selectedId = (int) model.getValueAt(i, 0);
                nameField.setText((String) model.getValueAt(i, 1));
                emailField.setText((String) model.getValueAt(i, 2));
                phoneField.setText((String) model.getValueAt(i, 3));
                deptField.setText((String) model.getValueAt(i, 4));
                posField.setText((String) model.getValueAt(i, 5));
                salaryField.setText(model.getValueAt(i, 6).toString());
            }
        });
    }

    private void loadTable() {
        model.setRowCount(0);
        List<Employee> list = dao.getAllEmployees();
        for (Employee e : list) {
            model.addRow(new Object[]{e.getId(), e.getName(), e.getEmail(), e.getPhone(), e.getDepartment(), e.getPosition(), e.getSalary()});
        }
    }

    private void addEmployee() {
        Employee e = new Employee(0, nameField.getText(), emailField.getText(), phoneField.getText(),
                deptField.getText(), posField.getText(), Double.parseDouble(salaryField.getText()));
        dao.addEmployee(e);
        loadTable();
        clearForm();
    }

    private void updateEmployee() {
        if (selectedId == -1) return;
        Employee e = new Employee(selectedId, nameField.getText(), emailField.getText(), phoneField.getText(),
                deptField.getText(), posField.getText(), Double.parseDouble(salaryField.getText()));
        dao.updateEmployee(e);
        loadTable();
        clearForm();
    }

    private void deleteEmployee() {
        if (selectedId != -1) {
            dao.deleteEmployee(selectedId);
            loadTable();
            clearForm();
        }
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        deptField.setText("");
        posField.setText("");
        salaryField.setText("");
        selectedId = -1;
    }
}
