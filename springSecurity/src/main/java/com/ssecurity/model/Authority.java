package com.ssecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Authority")
public class Authority {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String authority;
	
	
	public Authority()
	{
		
	}
	
	

	public Authority(String authority) {
		super();
		this.authority = authority;
	}



	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
