package de.is.project.shop.impl.domain;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;

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
	public void setCustomer(Customer customer) {
		this.customer=customer;
	}

	@Override
	public String getStreet() {
		return this.street;
	}

	@Override
	public void setStreet(String street) {
		this.street=street;
	}

	@Override
	public String getStreetNumber() {
		return this.streetNumber;
	}

	@Override
	public void setStreetNumber(String streetNumber) {
		this.streetNumber=streetNumber;
	}

	@Override
	public String getZipCode() {
		return this.zipCode;
	}

	@Override
	public void setZipCode(String zipCode) {
		this.zipCode=zipCode;
	}

	@Override
	public String getCountry() {
		return this.country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

}
