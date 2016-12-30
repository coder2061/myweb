package com.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = -1002654687284921717L;

	private String id;
	
    private String name;
    
    private String pswd;
    
	public User() {
		
	}
	
	public User(String id, String name, String pswd) {
		this.id = id;
		this.name = name;
		this.pswd = pswd;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="name", length=18)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="pswd", length=32)
	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
    
}
