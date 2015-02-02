package com.shtz.service;

import com.shtz.model.ArrivalItems;

public interface ArrivalItemsService {
	
	public void addArrival(ArrivalItems arrivalItems);
	
	public void deleteArrivalItems(int id);
	
	public ArrivalItems getArrivalItemById(int id);
	
	public void modifyArrivalItemDelBill(int id);
	
	public void modifyArrivalItem(ArrivalItems arrivalItems);
	
	public void addcfArrivalItems(int id,Double[] count);
}
