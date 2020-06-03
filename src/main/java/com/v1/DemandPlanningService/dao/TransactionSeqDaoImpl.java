package com.v1.DemandPlanningService.dao;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.v1.DemandPlanningService.bean.TransactionSeq;
import com.v1.DemandPlanningService.constant.DpsConstant;
import com.v1.DemandPlanningService.mapper.TransactionSeqBeanMapper;

@PropertySource(value="classpath:productSetting.properties",ignoreResourceNotFound=true)
@Repository
public class TransactionSeqDaoImpl implements CURDRepository<TransactionSeq> {

	private static final Logger logger = Logger.getLogger(TransactionSeqDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment propSource;
	
	@Override
	public TransactionSeq save(TransactionSeq obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionSeq update(TransactionSeq obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionSeq getByID(Object categoryId, Object status) {
		return null;
	}
	
	
	public List<TransactionSeq> getAllByID(Object categoryId , Date firsDay , Date lastDay) {
		logger.debug("ProductParamMasterDaoImpl (getTransactionId) Execution start.");
		List<TransactionSeq> transactionIdList = null;
		try {
			transactionIdList =  jdbcTemplate.query(propSource.getProperty("getTransactionIdByCategory"), new Object[] {(String)categoryId, firsDay, lastDay}, new TransactionSeqBeanMapper());	
		}catch(Exception e) {
			System.out.println("Exception while get transaction :");
			e.printStackTrace();
			logger.error("ProductParamMasterDaoImpl (getTransactionId) Exception :",e);
		}
		return transactionIdList;
	}

	@Override
	public TransactionSeq getByName(Object obj, Object status) {
		logger.debug(" TransactionSeq (getByName) Execution start.");
		String company = (String)obj;
		TransactionSeq transactionSeqBean = null;
		try {
			transactionSeqBean = jdbcTemplate.queryForObject(propSource.getProperty("getTransactionId"), new Object[] {}, new TransactionSeqBeanMapper());
			if("DPS".equalsIgnoreCase(company)) {
				transactionSeqBean.setTransaction_id(DpsConstant.DPS_COMPANY_PREFIX+ "_" + transactionSeqBean.getTransaction_id());	
			}
		}catch(Exception e) {
			logger.error("(getTemplateTypeBeanDetailDao) Exception occurs.",e);
			return null;
		}
		return transactionSeqBean;
	}

	@Override
	public TransactionSeq delete(TransactionSeq obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TransactionSeq checkMasterEntryMonthWise(String ref_prod_cat_id , String catalogNo ,Date from , Date to ) {
		logger.info("checkMasterEntryMonthWise() Execution start."); 
		TransactionSeq transactionSeq = null;
		if("".equals(catalogNo)) {
			catalogNo= null;
		}
		try {
			/*System.out.println("get trans query is L"+propSource.getProperty("getTransactionIdBasedOnProductAndCategory"));
			System.out.println("ref_prod_cat_id :"+ref_prod_cat_id);
			System.out.println("catalogNo is :"+catalogNo);
			System.out.println("from is :"+from);
			System.out.println("to is :"+to);*/
			transactionSeq = jdbcTemplate.queryForObject(propSource.getProperty("getTransactionIdBasedOnProductAndCategory"), new Object[] { ref_prod_cat_id , catalogNo == null ? "-" : catalogNo  , String.valueOf(from) , String.valueOf(to) }, new TransactionSeqBeanMapper());
			
			//System.err.println("get month wise is :"+transactionSeq);
		}catch(Exception e) {
			logger.error("checkMasterEntryMonthWise() Execution Ends.",e);
		}
		return transactionSeq;
	}
	
}
