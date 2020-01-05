package com.joydeep.hibernate.client;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.joydeep.hibernate.model.UserDetails;
import com.joydeep.hibernate.model.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		//
		UserDetails uds=new UserDetails();
		uds.setUserName("Joydeep Chowdhury");
	    Vehicle vehicle1=new Vehicle();
		vehicle1.setVehicleName("BMW");
		vehicle1.setUser(uds);
		Vehicle vehicle2=new Vehicle();
		vehicle2.setVehicleName("AUDI");
		vehicle2.setUser(uds);
		ArrayList<Vehicle> vehicles1=(ArrayList<Vehicle>) uds.getVehicle();
		vehicles1.add(vehicle1);
		vehicles1.add(vehicle2);
		uds.setVehicle(vehicles1);
        SessionFactory sf=new Configuration().configure().buildSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        session.save(uds);
        session.save(vehicle1);
        session.save(vehicle2);
        session.getTransaction().commit();
        session.close();
        session=sf.openSession();
        Vehicle vehicleretrieved= session.get(Vehicle.class, 3);
        UserDetails retrievedUser=vehicleretrieved.getUser();
        System.out.println(retrievedUser.getUserName());
	}

}
