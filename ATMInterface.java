import java.util.Scanner;




    // Transaction class to store transaction details
class Transaction 
{
    private String type;
    private double amount;

    public Transaction(String type, double amount) 
    {
        this.type = type;
        this.amount = amount;
    }

    public String getType()
    {
        return type;
    }

    public double getAmount() 
    {
        return amount;
    }
}

// ATM class containing main functionalities
class ATM {
    private String userId;
    private String pin;
    private double balance;
    private Transaction[] transactions;
    private int transactionCount;

    public ATM(String userId, String pin) 
    {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactions = new Transaction[100];
        this.transactionCount = 0;
    }

    public boolean authenticate(String userId, String pin) 
    {
        return this.userId.equals(userId) && this.pin.equals(pin);
    }

    public void displayMenu() 
    {
        System.out.println("1. Transactions History");
        System.out.println("2. Withdrawal");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    public void showTransactions() 
    {
        System.out.println("Transaction History:");
        for (int i = 0; i < transactionCount; i++) 
        {
            Transaction transaction = transactions[i];
            System.out.println(transaction.getType() + ": " + transaction.getAmount());
        }
    }

    public void withdraw(double amount) 
    {
        if (balance >= amount) 
        {
            balance -= amount;
            transactions[transactionCount++] = new Transaction("Withdrawal", amount);
            System.out.println("Withdrawal successful. Your remaining balance is: " + balance);
        } else
        {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(double amount) 
    {
        balance += amount;
        transactions[transactionCount++] = new Transaction("Deposit", amount);
        System.out.println("Deposit successful. Your updated balance is: " + balance);
    }

    public void transfer(double amount) 
    {
        if (balance >= amount) 
        {
            balance -= amount;
            transactions[transactionCount++] = new Transaction("Transfer", amount);
            System.out.println("Transfer successful. Your remaining balance is: " + balance);
        } else 
        {
            System.out.println("Insufficient funds.");
        }
    }

    public double getBalance() 
    {
        return balance;
    }
}

// Main class to run the ATM system
public class Main 
{
    public static void main(String[] args) 
    {
    
        Scanner scanner = new Scanner(System.in);

        // Initialize ATM with user ID and PIN
        ATM atm = new ATM("user321", "0808");

        // Authentication
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (atm.authenticate(userId, pin)) 
        {
            System.out.println("Authentication successful.");
            int choice;

            // Main menu
            do 
            {
                atm.displayMenu();
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) 
                {
                    case 1:
                        atm.showTransactions();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        atm.transfer(transferAmount);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } else 
        {
            System.out.println("Authentication failed. Exiting...");
        }
    }
}
