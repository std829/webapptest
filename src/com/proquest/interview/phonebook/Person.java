package com.proquest.interview.phonebook;

public class Person {
	public String name;
	public String phoneNumber;
	public String address;
	
	public Person() {
		name = "Name Surname";
		phoneNumber = "(000) 123-4567";
		address = "1 One Street, Oneville, FI";
	}
	
	public Person(String name, String phoneNumber, String address){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public String setPhoneNumber(){
		return(this.phoneNumber = phoneNumber);
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String setAddress(){
		return(this.address = address);
	}
	
	@Override
    public String toString(){
        return  '\n' + name + ", " + phoneNumber + ", " + address;
    }

	
}
