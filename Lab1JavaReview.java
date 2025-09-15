import java.util.Random;
import java.util.Scanner;

public class Lab1JavaReview {

    
    static int Bookcap = 5;
    static String[] bookTitles = new String[Bookcap];
    static String[] bookStatus = new String[Bookcap];
    static int[] bookSerialNumbers = new int[Bookcap];

    static int bookCount = 0;
    static Random rnd = new Random();

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int choice;

        while (true) {
            try {
                
                System.out.println("   ___                                  ");
                System.out.println("  / __\\__ _ _ __ ___  _ __  _   _ ___   ");
                System.out.println(" / /  / _' | '_ '' _ \\| '_ \\| | | / __|  ");
                System.out.println("/ /__| (_| | | | | | | |_) | |_| \\__ \\  ");
                System.out.println("\\____/\\__,_|_| |_| |_| .__/ \\__,_|___/  ");
                System.out.println("                     |_|                ");
                System.out.println("   __ _ _                               ");
                System.out.println("  / /(_) |__  _ __ __ _ _ __ _   _      ");
                System.out.println(" / / | | '_ \\| '__/ _' | '__| | | |     ");
                System.out.println("/ /__| | |_) | | | (_| | |  | |_| |     ");
                System.out.println("\\____/_|_.__/|_|  \\__,_|_|   \\__, |     ");
                System.out.println("                             |___/       ");
                System.out.println("1. Add Book");
                System.out.println("2. Update Book Status");
                System.out.println("3. Show All Books");
                System.out.println("4. Generate Report");
                System.out.println("5. Exit\n");
                System.out.print("Enter an action (Choose a number from 1-5):");

                choice = Integer.parseInt(scnr.nextLine().trim());

                switch (choice) {
                    case 1:
                        addBook(scnr);
                        break;
                    case 2:
                        updateBookStatus(scnr);
                        break;
                    case 3:
                        showBooks();
                        break;
                    case 4:
                        generateReport();
                        break;
                    case 5:
                        System.out.println("Thank you for using the Campus Library System, See you next time...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error :( ");
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // Method to add book
    public static void addBook(Scanner scnr) {
        if (bookCount >= Bookcap) {
            System.out.println("==============================\nBook limit reached. Cannot add more books.\n==============================");
            return;
        }
        System.out.println("________________________________");
        System.out.println("\nEnter book title: ");
        System.out.println("________________________________");
        String title = scnr.nextLine().trim();

        
        int serialNumber;
        do {
            serialNumber = rnd.nextInt(99998) + 1;
        } while (isSerialNumberUsed(serialNumber));

        bookTitles[bookCount] = title;
        bookStatus[bookCount] = "[Available]";
        bookSerialNumbers[bookCount] = serialNumber;

        bookCount++;
        System.out.println("\n________________________________");
        System.out.println("\n|          Library       | ");
        System.out.println("________________________________\n");
        System.out.println("Book \"" + title + "\" Has been added successfully with Serial Number: " + serialNumber +"\nTaking You back to the Main menu...\n");
    }

    
    public static boolean isSerialNumberUsed(int serial) {
        for (int i = 0; i < bookCount; i++) {
            if (bookSerialNumbers[i] == serial) {
                return true;
            }
        }
        return false;
    }

    // Method for book status
    public static void updateBookStatus(Scanner scnr) {
        if (bookCount == 0) {
            System.out.println("Error :( ");
            System.out.println("\nNo books available to update. Please Try Again" + "\nTaking you back to the Main menu\n");
            return;
        }

        showBooks();
        System.out.print("Enter the book Serial Number: ");
        String input = scnr.nextLine().trim();

        int index = -1;

        try {
            
            int serial = Integer.parseInt(input);
            for (int i = 0; i < bookCount; i++) {
                if (bookSerialNumbers[i] == serial) {
                    index = i;
                    break;
                }
            }
        } catch (NumberFormatException e) {
        System.out.println("\nError :(");    
        System.out.println("Please try to input serial number\n");  
        return;
        }

        if (index <= -1) {
            System.out.println("Book not found. Please check the Serial Number");
            return;
        }

        
        if (bookStatus[index].equals("[Available]")) {
            bookStatus[index] = "[Borrowed]" ;
        } else {
            bookStatus[index] = "[Available]";
        }

        System.out.println("Book \"" + bookTitles[index] + "\" (SN: " + bookSerialNumbers[index] +
                ") status updated to '" + bookStatus[index] + "'.");
    }

    
    public static void showBooks() {
        if (bookCount == 0) {
            System.out.println("No books to display...");
            return;
        }

        System.out.println("\n________________________________");
        System.out.println("\n|        List of Books          | ");
        System.out.println("________________________________\n");
        for (int i = 0; i < bookCount; i++) {
            System.out.println((i + 1) + ". [SN: " + bookSerialNumbers[i] + "] " +
                    bookTitles[i] + " - " + bookStatus[i] + "\n==============================");
        }
    }

    // Method for book report
    public static void generateReport() {
        int available = 0, borrowed = 0;
        for (int i = 0; i < bookCount; i++) {
            if (bookStatus[i].equals("[Available]")) {
                available++;
            } else {
                borrowed++;
            }
        }

        System.out.println("\n________________________________");
        System.out.println("\n|         Library Report        | ");
        System.out.println("________________________________\n");
        System.out.println("Total Books: " + bookCount + "\n==============================");
        System.out.println("Available Books: " + available + "\n==============================");
        System.out.println("Borrowed Books: " + borrowed + "\n==============================");
    }
}


