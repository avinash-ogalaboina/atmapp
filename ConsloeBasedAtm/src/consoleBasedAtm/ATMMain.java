package consoleBasedAtm;

import java.util.Scanner;

public class ATMMain {

    static Account users[] = new Account[1000];
    static int index = 0;
    Scanner sc = new Scanner(System.in);

    // Bold and bright console colors
    public static final String RESET = "\u001B[0m";
    public static final String BRIGHT_RED = "\033[1;91m";
    public static final String BRIGHT_GREEN = "\033[1;92m";
    public static final String BRIGHT_YELLOW = "\033[1;93m";
    public static final String BRIGHT_CYAN = "\033[1;96m";
    static final String CYAN_BOLD = "\u001B[1;36m";

    // Check for duplicate account number
    public boolean isDuplicateAccount(String accNum) {
        for (int i = 0; i < index; i++) {
            if (users[i].getaccountNumber().equals(accNum)) {
                return true;
            }
        }
        return false;
    }

    // Check for duplicate PIN
    public boolean isDuplicatePin(String pin) {
        for (int i = 0; i < index; i++) {
            if (users[i].getpin().equals(pin)) {
                return true;
            }
        }
        return false;
    }

    // Create a user account
    public void CreateUsers() throws Duplicate, InvalidAmount {

        System.out.println( CYAN_BOLD + "\n========== CREATE NEW ACCOUNT ==========" + RESET);

        System.out.print(BRIGHT_YELLOW + "ENTER USER NAME: " + RESET);
        String username = sc.next();

        System.out.print(BRIGHT_YELLOW + "ENTER USER LOCATION: " + RESET);
        String location = sc.next();

        String accountNumber;
        System.out.print(BRIGHT_YELLOW + "ENTER UNIQUE ACCOUNT NUMBER: " + RESET);
        accountNumber = sc.next();

        if (isDuplicateAccount(accountNumber)) {
            throw new Duplicate(BRIGHT_RED + "⚠ ACCOUNT NUMBER ALREADY EXISTS. PLEASE ENTER A DIFFERENT ACCOUNT NUMBER." + RESET);
        }

        String pin;
        System.out.print(BRIGHT_YELLOW + "CREATE A 4-DIGIT SECURE PIN: " + RESET);
        pin = sc.next();

        if (isDuplicatePin(pin)) {
            throw new Duplicate(BRIGHT_RED + "⚠ PIN ALREADY IN USE. PLEASE USE A DIFFERENT PIN." + RESET);
        }

        System.out.print(BRIGHT_YELLOW + "ENTER INITIAL DEPOSIT AMOUNT: " + RESET);
        double balance = sc.nextDouble();

        Account account = new Account(username, location, accountNumber, pin, balance);
        users[index++] = account;

        System.out.println(BRIGHT_GREEN + "\n✔ ACCOUNT CREATED SUCCESSFULLY!" + RESET);
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATMMain atmMain = new ATMMain();
        Admin admin = new Admin(atmMain);
        AccountLogin accountLogin = new AccountLogin();

        while (true) {
            System.out.println( CYAN_BOLD+ "\n========== WELCOME TO SECURE ATM SYSTEM ==========" + RESET);
            System.out.println(BRIGHT_CYAN+ "1. CREATE NEW ACCOUNT" + RESET);
            System.out.println(BRIGHT_CYAN + "2. ADMIN LOGIN" + RESET);
            System.out.println(BRIGHT_CYAN + "3. ACCOUNT HOLDER LOGIN" + RESET);
            System.out.println(BRIGHT_CYAN + "4. EXIT SYSTEM" + RESET);
            System.out.print(BRIGHT_YELLOW + "PLEASE SELECT AN OPTION (1-4): " + RESET);

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    try {
                        atmMain.CreateUsers();
                    } catch (Duplicate | InvalidAmount e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    admin.AdminLogin();
                    break;

                case 3:
                    accountLogin.accountlogin();
                    break;

                case 4:
                    System.out.println(BRIGHT_GREEN + "\n✔ THANK YOU FOR USING THE ATM SYSTEM. HAVE A GREAT DAY!" + RESET);
                    return;

                default:
                    System.out.println(BRIGHT_RED + "\n✘ INVALID OPTION SELECTED. PLEASE TRY AGAIN." + RESET);
                    break;
            }
        }
    }
}
