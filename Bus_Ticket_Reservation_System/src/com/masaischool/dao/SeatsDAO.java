package com.masaischool.dao;

import com.masaischool.dto.SeatsDTO;
import com.masaischool.exception.SomethingWentWrongException;

public interface SeatsDAO {
	public void entreSeats(SeatsDTO seatsDto) throws SomethingWentWrongException;
}
