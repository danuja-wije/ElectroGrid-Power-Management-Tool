package model;

public class Payment {
	private String user_ID;
	private int bill_ID;
	private String transaction_date;
	private float amount;
	private String card_number;
	private long payment_ID;

	//Constructor
	public Payment(String user_ID, int bill_ID, String transaction_date, float amount, String card_number, long payment_ID) {
		super();
		this.user_ID = user_ID;
		this.bill_ID = bill_ID;
		this.transaction_date = transaction_date;
		this.amount = amount;
		this.card_number = card_number;
		this.payment_ID = payment_ID;
	}

	public Payment(String user_ID, int bill_ID, float amount, String card_number) {
		this.user_ID = user_ID;
		this.bill_ID = bill_ID;
		this.amount = amount;
		this.card_number = card_number;
	}

	//Getters and Setters
	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public int getBill_ID() {
		return bill_ID;
	}

	public void setBill_ID(int bill_ID) {
		this.bill_ID = bill_ID;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public long getPayment_ID() {
		return payment_ID;
	}

	public void setPayment_ID(long payment_ID) {
		this.payment_ID = payment_ID;
	}

}
