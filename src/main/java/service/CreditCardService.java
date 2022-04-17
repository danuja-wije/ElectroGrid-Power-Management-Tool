package service;

import model.CreditCard;

public interface CreditCardService {
	public String insertCreditCard(CreditCard card);
	public String deleteCard(long cardNumber);
	public CreditCard viewCard();
	public String updateCard(CreditCard card);
}
