package com.qa.data;

public class Users {
	
	String name, job, ID;
	
	public Users(){
		
		
	}
	
 public Users(String name, String job, String ID){
		
	this.name=name;	
	this.job=job;
	this.ID=ID;
		
	}

	public String getName() {
		return name;
	}
	public String getID() {
		return ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}

//getter and setter method
 
  


}
