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
import com.ge.predix.digitalyst.entity.Lighter;
import com.ge.predix.digitalyst.repository.DigitalystModelRep;



@Service
public class LighterService {
	
	private static Log log = LogFactory.getLog(LighterService.class);
	@Autowired
	private com.ge.predix.digitalyst.repository.LighterModelRep modelRep;	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveLighterModel(Lighter model)
	{
	    log.info("Start executing  service");
		if(null != modelRep.save(model)){
			return true;
		}
		return false;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Lighter> readLighterModel()
	{
		log.info("Start executing  service");
		return modelRep.findAll();
		
	}
	
	

}
