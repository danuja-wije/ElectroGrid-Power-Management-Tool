package service;

import java.util.ArrayList;

import model.Payment;

public interface PaymentService {
	
	public String insertPayment(Payment payment);
	public ArrayList<Payment> getPayments(String UID);

}
