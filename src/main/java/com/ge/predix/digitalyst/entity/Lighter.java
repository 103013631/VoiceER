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
@Table(name ="temp_er")
//@NamedQuery(name="DigitalystModel.findByDomainAndsubDomain", 
//##query="select model from DigitalystModel model where model.domainName = ?1 and model.subDomainName = ?2")
public class Lighter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="er_number")
	private int erNumber;
	
	@Column(name="unit_serial_number")
	private int unitSerialNumber;
	
	@Column(name="er_desc")
	private String erDescription;
	
	@Column(name="er_desired_deliverable")
	private String erDesiredDeliverable;
	
	@Column(name="er_resolve_notes")
	private String erResolveNotes;
	

	public int getErNumber() {
		return erNumber;
	}

	public String getErDescription() {
		return erDescription;
	}

	public String getErDesiredDeliverable() {
		return erDesiredDeliverable;
	}

	public String getErResolveNotes() {
		return erResolveNotes;
	}
	
	public void setErNumber(int erNumber) {
		this.erNumber = erNumber;
	}

	public void setErDescription(String erDescription) {
		this.erDescription = erDescription;
	}
	
	public void setErDesiredDeliverable(String erDesiredDeliverable) {
		this.erDesiredDeliverable = erDesiredDeliverable;
	}	

	public void setErResolveNotes(String erResolveNotes) {
		this.erResolveNotes = erResolveNotes;
	}	



}
