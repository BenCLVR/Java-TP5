package ynov.java.bank.modele;

public class BankTrades {

	int id;
	int user_id;
	int account_id;
	User user;
	BankAccount bankAccount;
	BankTradesType type;
	double amount;

	public BankTrades(int id, User user, BankAccount bankAccount, BankTradesType type, double amount) {
		super();
		this.id = id;
		this.user = user;
		this.bankAccount = bankAccount;
		this.type = type;
		this.amount = amount;
	}

	public BankTrades(int id, int user_id, int account_id, BankTradesType type, double amount) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.account_id = account_id;
		this.type = type;
		this.amount = amount;
	}

	public BankTrades(int userId, BankTradesType type, double amount) {
		this.user_id = userId;
		this.type = type;
		this.amount = amount;
	}

	public static BankTradesType getBankTradesTypeFromString(String str) {
		if (str.equals("ADD")) {
			return BankTradesType.ADD;
		} else {
			return BankTradesType.REMOVE;
		}
	}

}
