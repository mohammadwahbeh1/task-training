import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        library.loadFromFile("books.txt");

        while (running) {
            //  menu
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Delete Book by Title");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    Book newBook = new Book(title, author, year);
                    library.addBook(newBook);
                    library.saveToFile("books.txt");
                    break;

                case 2:
                    // Show All Books
                    library.displayBooks();
                    break;

                case 3:
                    // Search Book by Title
                    System.out.print("Enter Title to Search:");
                    String searchTitle = scanner.nextLine();
                    library.searchBookByTitle(searchTitle);
                    break;

                case 4:
                    // Delete Book by Title
                    System.out.print("Enter Book Title to Delete: ");
                    String deleteTitle = scanner.nextLine();
                    library.deleteBookByTitle(deleteTitle);
                    library.saveToFile("books.txt");
                    break;

                case 5:
                    // Exit
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select from 1 to 5.");
            }
        }

        scanner.close();
    }
}
