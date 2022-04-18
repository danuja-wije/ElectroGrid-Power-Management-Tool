package service;

import java.util.ArrayList;

import model.CreditCard;

public interface CreditCardService {
	public String insertCreditCard(CreditCard card);
	public String deleteCard(long cardNumber);
	public ArrayList<CreditCard> viewCards(String user_ID);
	public String updateCard(long cardNumber, CreditCard card);
}
