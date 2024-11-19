
package ui;

import model.Book;
import model.BookManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BookStoreGUI extends JFrame {
    private static final String JSON_STORE = "./data/bookstore.json";
    private BookManager bookManager;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private JTextField nameField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextArea displayArea;

    public BookStoreGUI() {
        super("Book Store App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);  // 增加宽度以适应图片
        setLayout(new BorderLayout());

        bookManager = new BookManager();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        // 提示框提醒用户加载数据
        int option = JOptionPane.showConfirmDialog(this, "Do you want to load data?", "Load Data", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            loadData();
        }

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        topPanel.add(new JLabel("Book Name:"));
        nameField = new JTextField();
        topPanel.add(nameField);

        topPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        topPanel.add(authorField);

        topPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        topPanel.add(priceField);

        // Center panel with a text area and image
        JPanel centerPanel = new JPanel(new BorderLayout());
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // // Right side with image
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/image.jpg"));
        Image img = icon.getImage(); 

        Image scaledImg = img.getScaledInstance(200, 300, Image.SCALE_SMOOTH); 
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));

        // 设置标签的文本和 位置
        // imageLabel.setText("Welcome to Book Store");
        // imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        // imageLabel.setVerticalTextPosition(SwingConstants.TOP);


        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(imageLabel, BorderLayout.EAST);

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 7));

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton editButton = new JButton("Edit");
        JButton showButton = new JButton("Show All");
        JButton loadButton = new JButton("Load Data");
        JButton saveButton = new JButton("Save Data");
        JButton exitButton = new JButton("Exit");

        addButton.addActionListener(new AddButtonListener());
        removeButton.addActionListener(new RemoveButtonListener());
        editButton.addActionListener(new EditButtonListener());
        showButton.addActionListener(new ShowButtonListener());
        loadButton.addActionListener(new LoadButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
        exitButton.addActionListener(e -> {
            saveData();
            System.exit(0);
        });

        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(editButton);
        bottomPanel.add(showButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(saveButton);
        bottomPanel.add(exitButton);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Load data from file
    private void loadData() {
        try {
            bookManager = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Data loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to load data.");
        }
    }

    // Save data to file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookManager);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Data saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to save data.");
        }
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String author = authorField.getText();
            double price;
            try {
                price = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid price input.");
                return;
            }
            Book newBook = new Book(name, author, price);
            bookManager.addBook(newBook);
            JOptionPane.showMessageDialog(null, "Book added successfully.");
        }
    }

    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            bookManager.removeBook(name);
            JOptionPane.showMessageDialog(null, "Book removed successfully.");
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String newAuthor = authorField.getText();
            double newPrice;
            try {
                newPrice = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid price input.");
                return;
            }
            bookManager.editBook(name, newAuthor, newPrice);
            JOptionPane.showMessageDialog(null, "Book edited successfully.");
        }
    }

    private class ShowButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayArea.setText("");
            for (Book book : bookManager.getBooks()) {
                displayArea.append("Book Name: " + book.getBookName()
                        + ", Author: " + book.getAuthor()
                        + ", Price: $" + book.getPrice() + "\n");
            }
        }
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadData();
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveData();
        }
    }
}
