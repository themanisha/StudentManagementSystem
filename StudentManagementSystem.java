import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementSystem extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;

    public StudentManagementSystem() {
        super("Student Management System");

        // Initializes table model
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Grade"}, 0);
        table = new JTable(tableModel);

        // Creates UI components
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel formPanel = createForm();

        // Sets layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        // Sets frame properties
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createForm() {
        // Creates form components
        JTextField idField = new JTextField();
        idField.setPreferredSize(new Dimension(80, 25));

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 25));

        JTextField gradeField = new JTextField();
        gradeField.setPreferredSize(new Dimension(80, 25));

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent(idField.getText(), nameField.getText(), gradeField.getText());
            }
        });

        // Creates form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout());
        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Grade:"));
        formPanel.add(gradeField);
        formPanel.add(addButton);

        return formPanel;
    }

    private void addStudent(String id, String name, String grade) {
        // Validates input
        if (id.isEmpty() || name.isEmpty() || grade.isEmpty()) {
            showAlert("Please fill in all fields.");
            return;
        }

        // Adds a new student to the table
        tableModel.addRow(new Object[]{id, name, grade});

        // Clears input fields
        clearFields();
    }

    private void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystem());
    }
}
