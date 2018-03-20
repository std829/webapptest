package com.proquest.interview.phonebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
	public static List<Person> people = new ArrayList<Person>();
	
	//adds new Person info to the list
	public  void addPerson(Person newPerson) {
		people.add(newPerson);
	}
	
	//finds a Person with the given name from the list of People 
	@Override
	public Person findPerson(String firstName, String lastName) {
		String temp = firstName + " " + lastName;
		for (Person personWanted : people) {
		    if (temp.equals(personWanted.getName())){
		        return personWanted;
		    }
		}
		return null;
	}
	
	public static void main(String[] args) {
		DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database
		PhoneBookImpl me = new PhoneBookImpl();
		
		/* create person objects and put them in the PhoneBook and database
		 * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/ 
		Person personOne = new Person("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
		Person personTwo = new Person("Cynthia Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI");
		
		
		me.addPerson(personOne);
		me.addPerson(personTwo);
		
		// print the phone book out to System.out
        	System.out.println(people);
        
		
		/*Alternatively, if we want a formatted print, we can use the getters required from Person.Java*
		 *eg. If we need just the name of a Person we can use the following:****************************
		 	for (Person person: people){
			System.out.println(person.getName());
			}
		 **********************************************************************************************/
		
		//find Cynthia Smith and print out just her entry
       		Person findPerson = me.findPerson("Cynthia","Smith");
         	System.out.print("..:: printing entry ::..");
         	System.out.println(findPerson);
           
		//insert the new person objects into the database
         	try {
			Connection con = DatabaseUtil.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES (?, ?, ?)");
			Iterator<Person> it = people.iterator();
			while(it.hasNext()){
			     Person p = it.next();
			    ps.setString(1,p.getName());            
			    ps.setString(2,p.getPhoneNumber());
			    ps.setString(3,p.getAddress());
			    ps.addBatch();                      
			  }  
			int [] numUpdates = ps.executeBatch();
			  for (int i=0; i < numUpdates.length; i++) {
			      System.out.println("Execution " + i + 
			        " successful: " + numUpdates[i] + " rows updated");
			  }
			  con.commit();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
