package consoleBasedAtm;

import java.util.Scanner;

class Admin {

    static Scanner sc = new Scanner(System.in);
    ATMMain atmref;

    private static final String ADMIN_PIN = "1234";

    Admin(ATMMain atmref) {
        this.atmref = atmref;
    }

    public void AdminLogin() {
        System.out.print(ATMMain.BRIGHT_CYAN + "\nENTER ADMIN PIN TO PROCEED: " + ATMMain.RESET);
        String enterPin = sc.next();

        try {
            login(enterPin);
        } catch (CredentialsException e) {
            e.printStackTrace();
        }
    }

    public void login(String enteredPin) throws CredentialsException {
        if (ADMIN_PIN.equals(enteredPin)) {
            System.out.println(ATMMain.BRIGHT_GREEN + "\n✔ ADMIN LOGIN SUCCESSFUL" + ATMMain.RESET);

            while (true) {
                System.out.println(ATMMain. CYAN_BOLD+ "\n========== ADMIN MENU ==========" + ATMMain.RESET);
                System.out.println(ATMMain.BRIGHT_CYAN+ "1. VIEW ALL ACCOUNTS" + ATMMain.RESET);
                System.out.println(ATMMain.BRIGHT_CYAN + "2. DELETE AN ACCOUNT" + ATMMain.RESET);
                System.out.println(ATMMain.BRIGHT_CYAN + "3. LOG OUT" + ATMMain.RESET);
                System.out.print(ATMMain.BRIGHT_YELLOW + "SELECT AN OPTION (1-3): " + ATMMain.RESET);

                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        try {
                            viewAllAccounts();
                        } catch (NoUserFound e) {
                           e.printStackTrace();
                        }
                        break;

                    case 2:
                        try {
                            deleteAccount();
                        } catch (NoUserFound e) {
                           e.printStackTrace();
                        }
                        break;

                    case 3:
                        System.out.println(ATMMain.BRIGHT_GREEN + "\n✔ ADMIN LOGOUT SUCCESSFUL" + ATMMain.RESET);
                        return;

                    default:
                        System.out.println(ATMMain.BRIGHT_RED + "✘ INVALID OPTION. PLEASE SELECT BETWEEN 1 AND 3." + ATMMain.RESET);
                }
            }
        } else {
            throw new CredentialsException("Invalid Admin PIN");
        }
    }

    public void viewAllAccounts() throws NoUserFound {
        if (ATMMain.index > 0) {
            System.out.println(ATMMain.BRIGHT_CYAN + "\n====== LIST OF ALL USER ACCOUNTS ======" + ATMMain.RESET);

            for (int i = 0; i < ATMMain.index; i++) {
                Account user = ATMMain.users[i];
                System.out.println(String.format(ATMMain.BRIGHT_GREEN +
                        "ACCOUNT %d\n" +
                        "  ➤ NAME       : %s\n" +
                        "  ➤ LOCATION   : %s\n" +
                        "  ➤ ACCOUNT NO : %s\n" +
                        "  ➤ BALANCE    : ₹%.2f\n", i + 1,
                        user.getusername().toUpperCase(),
                        user.getlocation().toUpperCase(), 
                        user.getaccountNumber(),
                        user.getbalance()) + ATMMain.RESET);
            }
        } else {
            throw new NoUserFound("NO USER ACCOUNTS FOUND IN SYSTEM.");
        }
    }

    public void deleteAccount() throws NoUserFound {
        System.out.print(ATMMain.BRIGHT_YELLOW + "\nENTER ACCOUNT NUMBER TO DELETE: " + ATMMain.RESET);
        String accountNumber = sc.next();

        boolean isFound = false;

        for (int i = 0; i < ATMMain.index; i++) {
            if (ATMMain.users[i].getaccountNumber().equals(accountNumber)) {
                isFound = true;

                // Shift accounts after deleted one
                for (int j = i; j < ATMMain.index - 1; j++) {
                    ATMMain.users[j] = ATMMain.users[j + 1];
                }

                ATMMain.users[ATMMain.index - 1] = null;
                ATMMain.index--;

                System.out.println(ATMMain.BRIGHT_GREEN + "\n✔ ACCOUNT DELETED SUCCESSFULLY." + ATMMain.RESET);
                break;
            }
        }

        if (!isFound) {
            throw new NoUserFound("ACCOUNT NUMBER NOT FOUND. UNABLE TO DELETE.");
        }
    }
}
