package service;

import java.util.ArrayList;

import model.Inventory;

public interface InventoryService {
	
	public String insertInventory(Inventory inv);
	
	public ArrayList<Inventory> readInventory();

	public ArrayList<Inventory> readInventory(int specificID);

	public String updateInventory(int invID, Inventory inv);

	public String deleteInventory(int invID);
	

}
