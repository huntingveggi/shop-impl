package de.is.project.shop.impl.services;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.Request;
import de.is.project.shop.api.domain.RequestItem;
import de.is.project.shop.api.domain.Visitor;
import de.is.project.shop.api.services.RequestService;
import de.is.project.shop.impl.domain.RequestItemImpl;
import de.is.project.shop.impl.domain.VisitorImpl;

@Named
@Scope("prototype")
public class RequestServiceImpl implements RequestService {

	private Request request;

	@Override
	public void addProduct(Product product) {
		for (RequestItem item : request.getItems()) {
			if (item.getProduct().getId() == product.getId()) {
				item.setQuantity((item.getQuantity()) + 1);
				this.refreshRequest();
				return;
			}
		}
		RequestItem requestItem = new RequestItemImpl();
		requestItem.setProduct(product);
		request.getItems().add(requestItem);
		this.refreshRequest();
	}

	@Override
	public void removeProduct(Product product) {
		RequestItem removeItem = new RequestItemImpl();
		for (RequestItem item : request.getItems()) {
			if (item.getProduct().getId() == product.getId()) {
				if (item.getQuantity() > 1) {
					item.setQuantity(item.getQuantity() - 1);
					this.refreshRequest();
					return;
				} else {
					removeItem = item;
				}
				break;
			}
		}
		if (request.getItems().contains(removeItem)) {
			request.getItems().remove(removeItem);
		}
		this.refreshRequest();
	}

	@Override
	public void resetRequest() {
		this.getRequest().getItems().clear();
		refreshRequest();
	}

	@Override
	public void refreshRequest() {
		Visitor visitor = new VisitorImpl();
		this.request.accept(visitor);
	}

	@Override
	public void setRequest(Request request) {
		if (this.request == null) {
			this.request = request;
		}
	}

	@Override
	public Request getRequest() {
		return this.request;
	}

}
