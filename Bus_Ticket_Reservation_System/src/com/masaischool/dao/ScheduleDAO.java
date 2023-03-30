package com.masaischool.dao;

import com.masaischool.dto.ScheduleDTO;
import com.masaischool.dto.SeatsDTO;
import com.masaischool.exception.NoRecordFoundException;
import com.masaischool.exception.SomethingWentWrongException;

public interface ScheduleDAO {
	public void addSchedule(ScheduleDTO schDto) throws SomethingWentWrongException, NoRecordFoundException;
}
