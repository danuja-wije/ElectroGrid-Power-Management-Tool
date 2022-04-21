package service;

import java.util.ArrayList;

import model.Bill;

public interface BillService {
	
	public String generateBill(Bill bill);
	public String deleteBill(int bill_ID);
	public ArrayList<Bill> viewAllBills(String user_ID);
	public ArrayList<Bill> viewMonthlyBills(String user_ID, int year, String month);
	public String updateBill(int bill_ID, Bill bill);
	
}
