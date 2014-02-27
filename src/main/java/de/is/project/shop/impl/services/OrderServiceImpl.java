package de.is.project.shop.impl.services;

import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.is.project.shop.api.domain.BillOfDelivery;
import de.is.project.shop.api.domain.Invoice;
import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.Visitor;
import de.is.project.shop.api.persistence.OrderDAO;
import de.is.project.shop.api.persistence.ProductDAO;
import de.is.project.shop.api.services.OrderService;
import de.is.project.shop.impl.domain.BillOfDeliveryImpl;
import de.is.project.shop.impl.domain.InvoiceImpl;
import de.is.project.shop.impl.domain.OrderItemImpl;
import de.is.project.shop.impl.domain.OrderItemStatus;
import de.is.project.shop.impl.domain.OrderStatus;
import de.is.project.shop.impl.domain.VisitorImpl;

@Named
@Scope("prototype")
public class OrderServiceImpl implements OrderService {

	private Order order;

	@Inject
	ProductDAO productDAO;
	@Inject
	OrderDAO orderDAO;

	@Override
	public void addProduct(Product product) {
		boolean found = false;

		for (OrderItem item : this.order.getItems()){
			if(product.getId() < 1){
				IllegalArgumentException e = new IllegalArgumentException();
				throw e;
			}else{
				if (item.getProduct().getId() == product.getId()) {
					item.setQuantity(item.getQuantity() + 1);
					found = true;
					break;
				}
			}
		}
		if (!found) {
			OrderItem item = new OrderItemImpl();
			item.setProduct(product);
			item.setQuantity(1);
			item.setTotal(product.getPrice());
			this.order.getItems().add(item);
		}
		this.refreshOrder();

	}

	@Override
	public void removeProduct(Product product) {
		for (OrderItem item : this.order.getItems()) {
			if (item.getProduct().getId() == product.getId()) {
				if (item.getQuantity() < 2) {
					if (this.order.getItems().size() == 1) {
						this.order.getItems().clear();
					} else {
						this.order.getItems().remove(item);
					}
				} else {
					item.setQuantity(item.getQuantity() - 1);
				}
				break;
			}
		}
		this.refreshOrder();

	}

	@Override
	public void refreshOrder() {
		Visitor visitor = new VisitorImpl();
		this.order.accept(visitor);
	}

	@Override
	public void setOrder(Order order) {
		if (this.order == null) {
			this.order = order;
		}
	}

	@Override
	public Order getOrder() {
		return this.order;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Order placeOrder() {
		if (this.order != null) {
			this.order.setStatus(OrderStatus.IN_PROCESS.toString());
			for (OrderItem item : this.order.getItems()) {
				item.setStatus(OrderItemStatus.IN_PROCESS.toString());
				Product currentProduct = productDAO.findById(item.getProduct().getId());
				if (currentProduct.getStock() == 0) {
					item.setReservedQuantity(0);
				} else {
					if (currentProduct.getStock() < item.getQuantity()) {
						item.setReservedQuantity(currentProduct.getStock());
						currentProduct.setStock(0);
					} else {
						item.setReservedQuantity(item.getQuantity());
						currentProduct.setStock(item.getQuantity());
					}
					productDAO.persist(currentProduct);
				}
				item.setProduct(currentProduct);
			}
			this.order.setOrderDate(new Date());
			this.order = orderDAO.persist(this.order);
			return this.order;
		}
		return null;
	}

	@Override
	public BillOfDelivery createBillOfDeliveryForItems(
			Collection<OrderItem> orderItems) {
		BillOfDelivery billOfDelivery = new BillOfDeliveryImpl();
		billOfDelivery.setDeliveryDate(new Date());
		for (OrderItem item : orderItems) {
			if (item.getOrder().getId() == this.order.getId()) {
				for (OrderItem theItem : this.order.getItems()) {
					if (theItem.getId() == item.getId()) {
						billOfDelivery.getOrderItems().add(theItem);
						theItem.setBillOfDelivery(billOfDelivery);
					}
				}
			} else {
				// Throw Exception?
			}
		}
		this.order = orderDAO.persist(this.order);
		BillOfDelivery persistedBillOfDelivery = null;
		for (OrderItem item : this.order.getItems()) {
			if (item.getBillOfDelivery().getDeliveryDate() == billOfDelivery
					.getDeliveryDate()) {
				persistedBillOfDelivery = item.getBillOfDelivery();
			}
		}

		if (persistedBillOfDelivery == null) {
			return null;
		} else {
			return persistedBillOfDelivery;
		}

	}

	@Override
	public Invoice createInvoiceForItems(Collection<OrderItem> orderItems) {
		Invoice invoice = new InvoiceImpl();
		invoice.setInvoiceDate(new Date());
		for (OrderItem item : orderItems) {
			if (item.getOrder().getId() == this.order.getId()) {
				for (OrderItem theItem : this.order.getItems()) {
					if (theItem.getId() == item.getId()) {
						invoice.getOrderItems().add(theItem);
						theItem.setInvoice(invoice);
					}
				}
			} else {
				// Throw Exception?
			}
		}
		this.order = orderDAO.persist(this.order);
		Invoice persistedInvoice = null;
		for (OrderItem item : this.order.getItems()) {
			if (item.getInvoice().getInvoiceDate() == invoice.getInvoiceDate()) {
				persistedInvoice = item.getInvoice();
			}
		}

		if (persistedInvoice == null) {
			return null;
		} else {
			return persistedInvoice;
		}
	}

}