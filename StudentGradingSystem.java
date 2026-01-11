import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class StudentGradingSystem extends JFrame {
    private JTextField studentNameField, classField, subject1Field, subject2Field, subject3Field, subject4Field, subject5Field;
    private JButton calculateButton, loadButton;
    private JLabel gradeLabel, averageLabel, savedDataLabel;

    public StudentGradingSystem() {
        setTitle("Student Grading System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Student Name:");
        studentNameField = new JTextField(20);

        JLabel classLabel = new JLabel("Class:");
        classField = new JTextField(10);

        JLabel subject1Label = new JLabel("Subject 1 Marks:");
        subject1Field = new JTextField(5);

        JLabel subject2Label = new JLabel("Subject 2 Marks:");
        subject2Field = new JTextField(5);

        JLabel subject3Label = new JLabel("Subject 3 Marks:");
        subject3Field = new JTextField(5);

        JLabel subject4Label = new JLabel("Subject 4 Marks:");
        subject4Field = new JTextField(5);

        JLabel subject5Label = new JLabel("Subject 5 Marks:");
        subject5Field = new JTextField(5);

        calculateButton = new JButton("Calculate Grade");
        gradeLabel = new JLabel("Grade: ");
        averageLabel = new JLabel("Average: ");
        savedDataLabel = new JLabel("Saved Data: ");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = studentNameField.getText();
                String studentClass = classField.getText();
                try {
                    // Get marks from the input fields
                    double subject1 = Double.parseDouble(subject1Field.getText());
                    double subject2 = Double.parseDouble(subject2Field.getText());
                    double subject3 = Double.parseDouble(subject3Field.getText());
                    double subject4 = Double.parseDouble(subject4Field.getText());
                    double subject5 = Double.parseDouble(subject5Field.getText());

                    // Calculate average marks
                    double average = (subject1 + subject2 + subject3 + subject4 + subject5) / 5;
                    String grade = calculateGrade(average);

                    // Set the labels to show grade and average
                    gradeLabel.setText("Grade: " + grade);
                    averageLabel.setText("Average: " + String.format("%.2f", average));

                    // Save the student data to a file
                    saveData(studentName, studentClass, subject1, subject2, subject3, subject4, subject5, average, grade);

                    // Show result in GUI
                    JOptionPane.showMessageDialog(null, "Student: " + studentName + "\nClass: " + studentClass + 
                        "\nAverage Marks: " + average + "\nGrade: " + grade);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for marks.");
                }
            }
        });

        loadButton = new JButton("Load Saved Data");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load saved data and display on GUI
                loadData();
            }
        });

        add(nameLabel);
        add(studentNameField);
        add(classLabel);
        add(classField);
        add(subject1Label);
        add(subject1Field);
        add(subject2Label);
        add(subject2Field);
        add(subject3Label);
        add(subject3Field);
        add(subject4Label);
        add(subject4Field);
        add(subject5Label);
        add(subject5Field);
        add(calculateButton);
        add(gradeLabel);
        add(averageLabel);
        add(loadButton);
        add(savedDataLabel);

        setVisible(true);
    }

    private String calculateGrade(double average) {
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }

    private void saveData(String name, String studentClass, double subject1, double subject2, double subject3,
                        double subject4, double subject5, double average, String grade) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_data.txt", true))) {
            writer.write("Student: " + name + "\n");
            writer.write("Class: " + studentClass + "\n");
            writer.write("Subject 1 Marks: " + subject1 + "\n");
            writer.write("Subject 2 Marks: " + subject2 + "\n");
            writer.write("Subject 3 Marks: " + subject3 + "\n");
            writer.write("Subject 4 Marks: " + subject4 + "\n");
            writer.write("Subject 5 Marks: " + subject5 + "\n");
            writer.write("Average: " + average + "\n");
            writer.write("Grade: " + grade + "\n");
            writer.write("--------------------------------------------------\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage());
        }
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_data.txt"))) {
            String line;
            StringBuilder data = new StringBuilder("<html>Saved Data:<br>");
            while ((line = reader.readLine()) != null) {
                data.append(line).append("<br>");
            }
            data.append("</html>");
            savedDataLabel.setText(data.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentGradingSystem();
    }
}
