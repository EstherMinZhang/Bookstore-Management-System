# Bookstore Management System

## Answer Questions
 *Questions* list:
- What will the application do? 
   - It will be an application for a book store to managebookstore.  
   - This system will allow customer purchase books from bookstore.  
   - This system can search the name of the book and its authore name. 
   - This system will help manager to search and edit books list.
- Who will use it? 
   - The **cashiers or managers** working at the bookstore. 
- Why is this project interested to you?
   - It is *important* for business owners to manage their inventory.

## User Stories
- As a user, I want to be able to add a book to my bookstore.
- As a user, I want to be able to vew the list of books in my bookstore.
- As a user, I want to be able to edit the list of books in my bookstore.
- As a user, I want to be able to remove the list of books in my bookstore.
- As a user, I want to be able to save the list of books in my bookstore.
- As a user, I want to be able to load the list of books in my bookstore.

## Instructions for End User
- You can add multiple books to a BookManager by clicking the "Add" button after entering the book name, author, and price.
- You can generate the first required action related to the user story "edit the list of books in my bookstore" by clicking the "Edit" button after entering the book name, the new author, and the new price.
- You can generate the second required action related to the user story "remove the list of books in my bookstore" by clicking the "Remove" button after entering the book name.
- You can locate my visual component on the front page, which is a welcome picture.
- You can save the state of my application by clicking the "Save" button. Alternatively, when you click the "Exit" button, the system will ask if you want to save your progress.
- You can reload the state of my application by clicking the "Load" button. Alternatively, when you open the application, the system will ask if you want to load the previously saved data.

## Phase4: Task 2
- Wed Nov 27 14:47:17 PST 2024
Added book: ABC
- Wed Nov 27 14:47:27 PST 2024
Added book: aaa
- Wed Nov 27 14:47:34 PST 2024
Added book: aab
- Wed Nov 27 14:47:39 PST 2024
Remove book: aab
- Wed Nov 27 14:47:55 PST 2024
Edit book: ABC
- Wed Nov 27 14:47:58 PST 2024
All books printed.
- Wed Nov 27 14:48:16 PST 2024
- All books saved.

## Phase4: Task 3
If I had more time, I would refactor the design using the Composite Pattern. The idea is to create a unified structure that allows both individual books and categories to be managed uniformly, enhancing flexibility. First, I would define a common interface or abstract class called BookComponent, which would encapsulate all book-related operations such as adding, removing, or displaying information. This interface ensures that both individual books and categories are treated consistently.

The Book class would serve as the leaf node in this design. It represents individual books and implements the BookComponent interface. This class would handle basic operations like returning the book's name, price, or displaying its details. As a leaf node, it does not manage any child components.

The Category class would act as the composite node, also implementing the BookComponent interface. The Category class would take over the functionality of the BookManager class. It would represent categories of books, allowing for the inclusion of both individual books and subcategories. The Category class would manage child components by implementing methods for adding or removing children and performing operations (e.g., displaying details) across all child components.
