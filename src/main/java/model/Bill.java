package model;

public class Bill {

	//Attributes
	private String user_ID;
	private String account_ID;
	private int bill_ID;
	private int year;
	private String month;
	private String date_created;
	private float units;
	private float unit_price;
	private float charge;


	//Constructor
	public Bill(String user_ID, String account_ID, int bill_ID, int year, String month, float units, float unit_price, float charge) {
		super();
		this.user_ID = user_ID;
		this.account_ID = account_ID;
		this.bill_ID = bill_ID;
		this.year = year;
		this.month = month;
		this.units = units;
		this.unit_price = unit_price;
		this.charge = charge;
	}

	public Bill(String user_ID, String account_ID, int year, String month, float units, float unit_price, float charge) {
		super();
		this.user_ID = user_ID;
		this.account_ID = account_ID;
		this.year = year;
		this.month = month;
		this.units = units;
		this.unit_price = unit_price;
		this.charge = charge;
	}

	public Bill(float units, float unit_price, float charge) {
		this.units = units;
		this.unit_price = unit_price;
		this.charge = charge;
	}

	public Bill(int year, String month, String date_created, float units, float unit_price, float charge) {
		super();
		this.year = year;
		this.month = month;
		this.units = units;
		this.unit_price = unit_price;
		this.charge = charge;
		this.date_created = date_created;
	}

	public Bill(int bill_ID, int year, String month, String date_created, float units,
			float unit_price, float charge) {
		super();
		this.bill_ID = bill_ID;
		this.year = year;
		this.month = month;
		this.units = units;
		this.unit_price = unit_price;
		this.charge = charge;
		this.date_created = date_created;
	}

	//Getters and setters
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public float getUnits() {
		return units;
	}

	public void setUnits(float units) {
		this.units = units;
	}

	public float getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(float unit_price) {
		this.unit_price = unit_price;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public String getAccount_ID() {
		return account_ID;
	}

	public void setAccount_ID(String account_ID) {
		this.account_ID = account_ID;
	}
	
}
