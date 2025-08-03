import java.io.*;
import java.util.ArrayList;

public class Library {
    private final ArrayList<Book> books;


    public Library() {
        books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }


    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
        } else {
            for (Book book : books) {
                System.out.println("ID: " + book.getId() +
                        ", Title: " + book.getTitle() +
                        ", Author: " + book.getAuthor() +
                        ", Year: " + book.getYear());
            }
        }
    }

    // Search for a book by title
    public void searchBookByTitle(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: ID: " + book.getId() +
                        ", Title: " + book.getTitle() +
                        ", Author: " + book.getAuthor() +
                        ", Year: " + book.getYear());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with title: " + title);
        }
    }

    // Delete a book by ID
    public void deleteBookByTitle(String title) {
        String search = title.trim();
        boolean removed = books.removeIf(book -> book.getTitle().equalsIgnoreCase(search));

        if (removed) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("No book found with title: " + title);
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writer.println(book.getId() + "|" + book.getTitle() + "|" + book.getAuthor() + "|" + book.getYear());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        books.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);

                    Book book = new Book( title, author, year);
                    books.add(book);
                }
            }
        } catch (FileNotFoundException e) {
            // File does not exist
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
