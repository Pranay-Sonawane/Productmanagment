package com.example.demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public class ProductdaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean saveProduct(Product product) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isAdded = false;
		
		try {
			if(product != null) {
				session.save(product);
				transaction.commit();
				isAdded = true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return isAdded;
	}

	
	
	@Override
	public Product getProductById(String productId) {
		
		Session session = sessionFactory.openSession();
		Product product = null;
		try {
			
			 product = session.get(Product.class, productId);
			System.out.println(product);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		finally {
			
			if(session != null) {
				
				session.close();
			}
		}
		
		return product;

	}



	@Override
	public List<Product> getAllProduct() {
		Session session = sessionFactory.openSession();
		List<Product> list = null;
		
		try {
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
				
			}
		}
		return list;
	}



	@Override
	public boolean deleteProductById(String productId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isDelete = false;
		
		try {
			Product product = session.get(Product.class, productId);
			session.delete(product);
			transaction.commit();
			isDelete = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if(session != null) {
				session.close();
			}
		}
		return isDelete;
	}



	@Override
	public boolean updateProduct(Product product) {
		
	Session session = sessionFactory.openSession();
	Transaction transaction = session.beginTransaction();
	boolean isUpdated = false;
	
	try {
		session.update(product);
		transaction.commit();
		isUpdated = true;
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if(session != null) {
			session.close();
		}
	}
		return isUpdated;
	}



	@Override
	public int uploadProductList(List<Product> list) {
		Session session = null;
		Transaction transaction = null;
		int count = 0;
		
		try {
			 
			 
			 for (Product product : list) {
				 session = sessionFactory.openSession();
				 transaction = session.beginTransaction();
				 session.save(product);
				 transaction.commit();
				 count = count+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return 0;
	}



	

	
	

}
