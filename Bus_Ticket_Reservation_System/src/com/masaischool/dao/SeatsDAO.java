package com.masaischool.dao;

import java.util.List;

import com.masaischool.dto.SeatsDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public interface SeatsDAO {
	public void entreSeats(SeatsDTO seatsDto) throws SomethingWentWrongException;
	public List<Integer> getAvailableSeats(String busNumber)throws SomethingWentWrongException, NoRecordFoundException;
	
}
