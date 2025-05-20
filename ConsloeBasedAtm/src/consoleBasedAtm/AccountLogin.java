package consoleBasedAtm;

import java.util.Scanner;

public class AccountLogin {

    String accountnumber;
    ATMMain accountLogin = new ATMMain();
    int indexval;

    Scanner sc = new Scanner(System.in);

    public void accountlogin() {
        System.out.print(ATMMain.BRIGHT_CYAN + "\nENTER USER ACCOUNT NUMBER: " + ATMMain.RESET);
        accountnumber = sc.next();
        System.out.print(ATMMain.BRIGHT_CYAN + "ENTER USER PIN: " + ATMMain.RESET);
        String pin = sc.next();

        try {
            perform(accountnumber, pin);
        } catch (CredentialsException | NoUserFound e) {
            e.printStackTrace();
        }
    }

    public void perform(String accountnumber, String pin) throws CredentialsException, NoUserFound {
        if (ATMMain.index > 0) {
            if (authenticateUser(accountnumber, pin)) {
                System.out.println(ATMMain.BRIGHT_GREEN + "\n✔ ACCOUNT LOGIN SUCCESSFUL" + ATMMain.RESET);
                System.out.println(ATMMain.BRIGHT_GREEN + "WELL-COME  "+ATMMain.users[indexval].getusername() + ATMMain.RESET);

                while (true) {
                    System.out.println(ATMMain. CYAN_BOLD + "\n========== USER MENU ==========" + ATMMain.RESET);
                    System.out.println(ATMMain.BRIGHT_CYAN + "1. WITHDRAW MONEY" + ATMMain.RESET);
                    System.out.println(ATMMain.BRIGHT_CYAN+ "2. DEPOSIT MONEY" + ATMMain.RESET);
                    System.out.println(ATMMain.BRIGHT_CYAN+ "3. CHECK ACCOUNT BALANCE" + ATMMain.RESET);
                    System.out.println(ATMMain.BRIGHT_CYAN + "4. LOG OUT" + ATMMain.RESET);
                    System.out.print(ATMMain.BRIGHT_YELLOW + "SELECT AN OPTION (1-4): " + ATMMain.RESET);

                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            try {
                                withdraw();
                            } catch (InvalidAmount e) {
                                e.printStackTrace();
                            }
                            break;

                        case 2:
                            try {
                                deposit();
                            } catch (InvalidAmount e) {
                                e.printStackTrace();
                            }
                            break;

                        case 3:
                            checkBalance();
                            break;

                        case 4:
                            System.out.println(ATMMain.BRIGHT_GREEN + "\n✔ USER LOGOUT SUCCESSFUL" + ATMMain.RESET);
                            return;

                        default:
                            System.out.println(ATMMain.BRIGHT_RED + "✘ INVALID OPTION. PLEASE SELECT BETWEEN 1 AND 4." + ATMMain.RESET);
                    }
                }
            } else {
                throw new CredentialsException("ACCOUNT NUMBER OR PIN IS INCORRECT");
            }
        } else {
            throw new NoUserFound("NO USERS AVAILABLE IN THE SYSTEM");
        }
    }

    public boolean authenticateUser(String accountNumber, String pin) {
        for (int i = 0; i < ATMMain.index; i++) {
            if (ATMMain.users[i].getaccountNumber().equals(accountNumber)
                    && ATMMain.users[i].getpin().equals(pin)) {
                indexval = i;
                return true;
            }
        }
        return false;
    }

    public void withdraw() throws InvalidAmount {
        System.out.print(ATMMain.BRIGHT_YELLOW + "\nENTER THE AMOUNT TO WITHDRAW: " + ATMMain.RESET);
        double amount = sc.nextDouble();

        if (amount > 0 && amount % 100 == 0 && amount <= ATMMain.users[indexval].getbalance()) {
            double updatedBalance = ATMMain.users[indexval].getbalance() - amount;
            ATMMain.users[indexval].setbalance(updatedBalance);
            System.out.println(ATMMain.BRIGHT_GREEN + "✔ WITHDRAWAL SUCCESSFUL. ₹" + amount + " DEDUCTED." + ATMMain.RESET);
        } else {
            if (amount > ATMMain.users[indexval].getbalance()) {
                throw new InvalidAmount("INSUFFICIENT FUNDS");
            } else {
                throw new InvalidAmount("AMOUNT MUST BE A POSITIVE MULTIPLE OF 100");
            }
        }
    }

    public void deposit() throws InvalidAmount {
        System.out.print(ATMMain.BRIGHT_YELLOW + "\nENTER THE AMOUNT TO DEPOSIT: " + ATMMain.RESET);
        double amount = sc.nextDouble();

        if (amount > 0 && amount % 100 == 0) {
            double updatedBalance = ATMMain.users[indexval].getbalance() + amount;
            ATMMain.users[indexval].setbalance(updatedBalance);
            System.out.println(ATMMain.BRIGHT_GREEN + "✔ DEPOSIT SUCCESSFUL. ₹" + amount + " ADDED." + ATMMain.RESET);
        } else {
            throw new InvalidAmount("AMOUNT MUST BE POSITIVE AND A MULTIPLE OF 100");
        }
    }

    public void checkBalance() {
        double balance = ATMMain.users[indexval].getbalance();
        System.out.println(ATMMain.BRIGHT_GREEN + String.format("\n💰 CURRENT ACCOUNT BALANCE: ₹%.2f", balance) + ATMMain.RESET);
    }
}
