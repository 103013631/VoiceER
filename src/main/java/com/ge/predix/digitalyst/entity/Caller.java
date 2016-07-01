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
public class Caller{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_phone")
	private int userPhone;
	
	@Column(name="user_name")
	private String userName;
	
	public int getUserId() {
		return userId;
	}

	public int getUserPhone() {
		return userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserPhone(int userPhone) {
		this.userPhone = userPhone;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}	

}
