package com.ge.predix.digitalyst.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ge.predix.digitalyst.entity.DigitalystModel;
import com.ge.predix.digitalyst.repository.DigitalystModelRep;
import com.ge.predix.digitalyst.entity.Caller;


@Service
public class CallerService {
	
	private static Log log = LogFactory.getLog(CallerService.class);
	@Autowired
	private com.ge.predix.digitalyst.repository.CallerModelRep modelRep;	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveCallerModel(Caller model)
	{
	    log.info("Start executing  service");
		if(null != modelRep.save(model)){
			return true;
		}
		return false;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Caller> readCallerModel()
	{
		log.info("Start executing  service");
		return modelRep.findAll();
		
	}
	
	

}
