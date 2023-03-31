package com.masaischool.dao;

import com.masaischool.dto.CustomerDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public interface CustomerDAO {
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;
	public void logout();
	public void signup(CustomerDTO cusDto) throws SomethingWentWrongException;
}
