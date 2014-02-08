package de.is.project.shop.impl.persistence;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractDAO {

	@Inject
	SessionFactory sessionFactory;
	Session session;

	protected Session getCurrentSession() {
		if (this.session == null) {
			this.session = sessionFactory.openSession();
		}
		return this.session;
	}

	protected void closeCurrentSession() {
		this.session.close();
	}

}
