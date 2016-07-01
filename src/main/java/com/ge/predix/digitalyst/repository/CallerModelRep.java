/*
 * Copyright (c) 2015 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
 
package com.ge.predix.digitalyst.repository;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ge.predix.digitalyst.entity.Caller;


/**
 * @author 
 */

@Repository
public interface CallerModelRep extends JpaRepository<Caller,Integer> {
	/*
		List<DigitalystModel> findByDomainAndsubDomain(String domainName,String subDomainName);
	*/
	

}
