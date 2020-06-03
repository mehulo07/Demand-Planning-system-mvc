package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.dto.YearMonthWiseDataCount;

public class YearMonthWiseDataCountMapper implements RowMapper<YearMonthWiseDataCount> {

	@Override
	public YearMonthWiseDataCount mapRow(ResultSet rs, int rowNum) throws SQLException {
		YearMonthWiseDataCount yearMonthWiseDataCount = new YearMonthWiseDataCount();
			yearMonthWiseDataCount.setYear(rs.getInt("YEAR"));
			yearMonthWiseDataCount.setMonth(rs.getInt("MONTH"));
			yearMonthWiseDataCount.setCount(rs.getInt("TOTAL"));
		return yearMonthWiseDataCount;
	}

}
