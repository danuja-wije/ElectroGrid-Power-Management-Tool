package service;

import java.util.ArrayList;

import model.Bill;

public interface BillService {
	
	public String generateBill(Bill bill);
	public String deleteBill(long bill_ID);
	public ArrayList<Bill> viewAllBills(String user_ID);
	public ArrayList<Bill> viewMonthlyBills(String user_ID, String Month);
	public String updateBill(String bill_ID, Bill bill);
	
}
