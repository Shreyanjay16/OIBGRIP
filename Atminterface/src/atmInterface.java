import java.util.Scanner;

class BankAccount {

	String name;
	String username;
	String password;
	String accountNo;
	float balance = 100000f;
	int transactions = 0;
	String transactionsHistory = "";

	public void register() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Your Name - ");
		this.name = sc.nextLine();
		System.out.print("\nEnter Your Username - ");
		this.username = sc.nextLine();
		System.out.print("\nEnter Your Password - ");
		this.password = sc.nextLine();
		System.out.print("\nEnter Your Account Number - ");
		this.accountNo = sc.nextLine();
		System.out.print("\nRegistration completed...kindly Login");
	}

	public boolean login() {
		boolean isLogin = false;
		Scanner sc = new Scanner(System.in);
		while ( !isLogin ) {
			System.out.print("\nEnter Your Username - ");
			String Username = sc.nextLine();
			if (Username.equals(username)){
				while ( !isLogin ){
					System.out.print("\nEnter Your Password - ");
					String Password = sc.nextLine();
					if (Password.equals(password)){
						isLogin = true;
					}else {
						System.out.print("\nIncorrect Password");
					}
				}
			}else {
				System.out.print("\nUsername Not found ");
			}
		}
		return isLogin;
	}

	public void withdraw() {

		System.out.print("\nEnter amount to withdraw - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();
		try {
			if (balance >= amount ){
				transactions++;
				balance -= amount;
				System.out.print("\nWithdraw Successful ");
				String str = amount + " Rs Withdrawn \n";
				transactionsHistory = transactionsHistory.concat(str);
			} else {
				System.out.print("\nInsufficient Balance ");
			}
		}
		catch ( Exception e ){
		}
	}

	public void deposit() {

		System.out.print("\nEnter amount to deposit - ");
		Scanner sc = new Scanner(System.in);
		float amount = sc.nextFloat();

		try {
			if ( amount <= 100000f ){
				transactions++;
				balance += amount;
				System.out.print("\nSuccessfully Deposited ");
				String str = amount + "Rs Deposited \n";
				transactionsHistory = transactionsHistory.concat(str);
			} else {
				System.out.print("\nSorry.... Limit is 100000.00");
			}
		}
		catch ( Exception e){
		}
	}
	public void transfer() {

		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Recipient's Name - ");
		String recipient = sc.nextLine();
		System.out.print("\nEnter Amount to Transfer - ");
		float amount = sc.nextFloat();

		try {
			if ( balance >= amount ){
				if ( amount <= 50000f ){
					transactions++;
					balance -= amount;
					System.out.print("\nSuccessfully Transferred to " + recipient);
					String str = amount + " Rs Transferred to " + recipient +"\n";
					transactionsHistory = transactionsHistory.concat(str);
				} else {
					System.out.print("\nSorry.... Limit is 50000.00");
				}
			} else {
				System.out.print("\nInsufficient Balance ");
			}
		}
		catch ( Exception e){
		}
	}

	public void checkBalance() {
		System.out.print("\n" + balance + "Rs");
	}

	public void transactionHistory() {
		if ( transactions == 0){
			System.out.print("\nEmpty ");
		} else {
			System.out.print("\n" + transactionsHistory);
		}
	}
}

public class atmInterface {

	public static int takeIntegerInput(int limit) {
		int input = 0;
		boolean flag = false;

		while ( !flag ){
			try{
				Scanner sc = new Scanner(System.in);
				input = sc.nextInt();
				flag = true;

				if ( flag && input > limit || input < 1 ) {
					System.out.println("\nChoose a number between 1 to " + limit);
					flag = false;
				}
			} catch ( Exception e ) {
				System.out.println("\nEnter Only Integer Value ");
			}
		}
		return input;
	}

	public static void main(String[] args) {
		System.out.println("\n********** WELCOME TO ATM SYSTEM **********\n");
		System.out.println("1.Register \n2.Exit ");
		System.out.print("Enter Your Choice - ");
		int choice = takeIntegerInput(2);

		if ( choice == 1 ){
			BankAccount b = new BankAccount();
			b.register();
			while (true) {
				System.out.println("\n1.Login \n2.Exit");
				System.out.println("Enter Your Choice - ");
				int ch = takeIntegerInput(2);
				if ( ch == 1 ){
					if ( b.login()) {
						System.out.println("\n\n**********WELCOME BACK " + b.name + "**********");
						boolean isFinished = false;
						while ( !isFinished ){
							System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
							System.out.println("\nEnter Your choice - ");
							int c = takeIntegerInput(6);
							switch (c) {
								case 1 :
									b.withdraw();
									break;
								case 2 :
									b.deposit();
									break;
								case 3 :
									b.transfer();
									break;
								case 4 :
									b.checkBalance();
									break;
								case 5 :
									b.transactionHistory();
									break;
								case 6 :
									isFinished = true;
									break;
							}
						}
					}
				} else {
					System.exit(0);
				}
			}
		} else {
			System.exit(0);
		}
	}

}
