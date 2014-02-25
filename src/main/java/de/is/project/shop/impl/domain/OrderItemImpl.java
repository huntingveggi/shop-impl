package de.is.project.shop.impl.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import de.is.project.shop.api.domain.BillOfDelivery;
import de.is.project.shop.api.domain.Invoice;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;
import de.is.project.shop.api.domain.Product;

@Entity(name = "order_items")
public class OrderItemImpl extends AbstractEntity implements OrderItem {

	Product product;
	int quantity;
	double price;
	int reservedQuantity;
	double discount;
	String status;
	double total;
	Order order;
	BillOfDelivery billOfDelivery;
	Invoice invoice;

	@Override
	@ManyToOne(targetEntity=BillOfDeliveryImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public BillOfDelivery getBillOfDelivery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Column(name = "discount", nullable = true)
	public double getDiscount() {
		return this.discount;
	}

	@Override
	@ManyToOne(targetEntity=InvoiceImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Invoice getInvoice() {
		return this.invoice;
	}

	@Override
	@ManyToOne(targetEntity = OrderImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Order getOrder() {
		return this.order;
	}

	@Override
	@Column(name = "price", nullable = false)
	public double getPrice() {
		return this.price;
	}

	@Override
	@ManyToOne(targetEntity = ProductImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Product getProduct() {
		return this.product;
	}

	@Override
	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	@Column(name = "reserved_quantity", nullable = false)
	public int getReservedQuantity() {
		return this.reservedQuantity;
	}

	@Override
	@Column(name = "status", nullable = true)
	public String getStatus() {
		return this.status;
	}

	@Override
	@Transient
	public double getTotal() {
		return this.total;
	}

	@Override
	public void setBillOfDelivery(BillOfDelivery billOfDelivery) {
		this.billOfDelivery=billOfDelivery;
	}

	@Override
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public void setInvoice(Invoice invoice) {
		this.invoice=invoice;
	}

	@Override
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setTotal(double total) {
		this.total = total;
	}

}
