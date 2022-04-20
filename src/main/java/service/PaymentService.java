package service;

import model.Payment;

public interface PaymentService {
	
	public String insertPayment(Payment payment);
	public String getPayments(String UID);

}
