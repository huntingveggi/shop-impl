package de.is.project.shop.impl.persistence;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractDAO {

	@Inject
	SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
