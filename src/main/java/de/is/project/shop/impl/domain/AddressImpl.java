package de.is.project.shop.impl.domain;

import de.is.project.shop.api.domain.Address;
import de.is.project.shop.api.domain.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "addresses")
public class AddressImpl extends AbstractEntity implements Address {

	Customer customer;
	String street;
	String streetNumber;
	String zipCode;
	String city;
	String country;

	@Override
	@Column(name = "city", nullable = false)
	public String getCity() {
		return this.city;
	}

	@Override
	@Column(name = "country", nullable = false)
	public String getCountry() {
		return this.country;
	}

	@Override
	@ManyToOne(targetEntity = CustomerImpl.class, cascade = { CascadeType.ALL })
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	@Column(name = "street", nullable = false)
	public String getStreet() {
		return this.street;
	}

	@Override
	@Column(name = "street_number", nullable = false)
	public String getStreetNumber() {
		return this.streetNumber;
	}

	@Override
	@Column(name = "zip_code", nullable = false)
	public String getZipCode() {
		return this.zipCode;
	}

	@Override
	public void setCity(String city) {
		this.city=city;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	@Override
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
