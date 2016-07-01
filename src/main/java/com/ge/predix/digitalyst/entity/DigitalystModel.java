package com.ge.predix.digitalyst.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author 
 *
 */
@Entity
@Table(name ="users")
//@NamedQuery(name="DigitalystModel.findByDomainAndsubDomain", 
//##query="select model from DigitalystModel model where model.domainName = ?1 and model.subDomainName = ?2")
public class DigitalystModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_phone")
	private String userPhone;
	
	
	

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserPhone() {
		return userPhone;
	}



	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}



	public void setId(int id) {
		this.id = id;
	}



}
