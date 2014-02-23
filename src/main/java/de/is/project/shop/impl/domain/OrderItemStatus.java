package de.is.project.shop.impl.domain;

public enum OrderItemStatus {

	IN_PROCESS("In Process"),
	ORDERED("Ordered"),
	PACKAGED("Packaged"),
    IN_DELIVERY("In Delivery"),
    DELIVERED("Delivered");

	OrderItemStatus(String displayName) {
        this.displayName = displayName;
    }
	
    private String displayName;

    public String displayName() { 
    	return displayName; 
    }

    @Override 
    public String toString() { 
    	return displayName; 
    }
}
