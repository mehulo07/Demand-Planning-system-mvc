package com.v1.DemandPlanningService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.v1.DemandPlanningService.bean.TransactionSeq;

public class TransactionSeqBeanMapper implements RowMapper<TransactionSeq> {

	@Override
	public TransactionSeq mapRow(ResultSet rs, int rowNum) throws SQLException {
		TransactionSeq transactionSeqBean = new TransactionSeq();
		transactionSeqBean.setTransaction_id(rs.getString("TRANSACTION_ID"));
		return transactionSeqBean;
	}

}
