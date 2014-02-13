package de.is.project.shop.impl.domain;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

public class AddressImpl extends AbstractEntity implements Address {

	Customer customer;
	String street;
	String streetNumber;
	String zipCode;
	String country;
	
	@Override
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	@ManyToMany(targetEntity = CustomerImpl.class)
	public void setCustomer(Customer customer) {
		this.customer=customer;
	}

	@Override
	@Column(name = "Street", nullable = false)
	public String getStreet() {
		return this.street;
	}

	@Override
	public void setStreet(String street) {
		this.street=street;
	}

	@Override
	@Column(name = "StreetNumber", nullable = false)
	public String getStreetNumber() {
		return this.streetNumber;
	}

	@Override
	public void setStreetNumber(String streetNumber) {
		this.streetNumber=streetNumber;
	}

	@Override
	@Column(name = "ZipCode", nullable = false)
	public String getZipCode() {
		return this.zipCode;
	}

	@Override
	public void setZipCode(String zipCode) {
		this.zipCode=zipCode;
	}

	@Override
	@Column(name = "Country", nullable = false)
	public String getCountry() {
		return this.country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

}
