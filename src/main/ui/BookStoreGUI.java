
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


// BookStoreGUI is the graphical user class for managing the bookstore.
// It allows the user to add, remove, edit, display, load, and save books in the store.
public class BookStoreGUI extends JFrame {
    private static final String JSON_STORE = "./data/bookstore.json";
    private BookManager bookManager;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private JTextField nameField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextArea displayArea;
   
    // Modifies: The GUI components (text fields, buttons, etc.)
    // Effects: Creates a window, sets up the layout, initializes the book manager, 
    // and handles data loading.
    @SuppressWarnings("methodlength")
    public BookStoreGUI() {
        super("Book Store App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 400);  // 增加宽度以适应图片
        setLayout(new BorderLayout());

        bookManager = new BookManager();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        int option = JOptionPane.showConfirmDialog(this, "Do you want to load data?", 
                "Load Data", JOptionPane.YES_NO_OPTION);
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
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showOptionDialog(
                        BookStoreGUI.this,
                        "Do you want to save changes before exiting?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Save and Exit", "Exit without Saving", "Cancel"},
                        "Save and Exit"
                );
        
                if (option == JOptionPane.YES_OPTION) { // Save and Exit
                    saveData();
                    System.exit(0);
                } else if (option == JOptionPane.NO_OPTION) { // Exit without Saving
                    System.exit(0);
                }
                // Cancel option does nothing
            }
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

    // Modifies: The bookManager object (sets the books list)
    // Effects: Reads the JSON file and loads the book data into the application.
    private void loadData() {
        try {
            bookManager = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Data loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to load data.");
        }
    }

    // Requires: A valid bookManager with books to save
    // Modifies: The file at JSON_STORE by writing book data into it
    // Effects: Writes the current list of books to the JSON file
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

    // Requires: The user to input valid book name, author, and price
    // Modifies: The bookManager by adding a new book
    // Effects: Adds a new book to the bookManager and shows a success message.
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
    
    // Requires: The user to input a valid book name
    // Modifies: The bookManager by removing a book
    // Effects: Removes the book from the bookManager and shows a success message.
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            bookManager.removeBook(name);
            JOptionPane.showMessageDialog(null, "Book removed successfully.");
        }
    }
    
    // Requires: The user to input a valid book name, new author, and new price
    // Modifies: The bookManager by editing an existing book
    // Effects: Edits the book in the bookManager and shows a success message.
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
    
    // Modifies: The displayArea (sets the text content)
    // Effects: Displays all books in the bookManager in the text area.
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
    
    // Modifies: The bookManager object (sets the books list)
    // Effects: Loads the book data into the application from the file.
    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadData();
        }
    }
    
    // Modifies: The file at JSON_STORE by writing book data into it
    // Effects: Saves the current book data to the JSON file.
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveData();
        }
    }
}
