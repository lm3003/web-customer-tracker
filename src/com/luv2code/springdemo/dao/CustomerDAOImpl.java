package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	//need to inject the hibernate session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomer() {
		//get the currect hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//create a query...sort data by last name
		Query<Customer> theQuery=session.createQuery("from Customer order by lastName",Customer.class);
		
		//get the customer list by executing the query
		List<Customer> customers=theQuery.getResultList();
		
		//return customer list
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		
		//save the customer...to the database
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		//retrieve or read from database using the primary key
		Customer theCustomer=currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void delete(int theId) {
		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		//delete the customer using the primary key
		Query theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
				
	}

}
