package com.masaischool.dao;

import com.masaischool.dto.BusDTO;
import com.masaischool.dto.ScheduleDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public interface BusDAO {
	
	public void addBus(BusDTO bus, ScheduleDTO sch) throws SomethingWentWrongException, NoRecordFoundException;
	public void updateBus(BusDTO bus) throws SomethingWentWrongException;
	public void deleteBus(String busId) throws SomethingWentWrongException;
//	public void  deleteBus(BusDTO bus) throws SomethingWentWrongException,NoRecordFoundException;
	
}
