package de.is.project.shop.impl.domain;

public enum OrderStatus {

	IN_PROCESS("In Process"),
    IN_DELIVERY("In Delivery"),
    DELIVERED("Delivered"),
	WAITING_FOR_PAYMENT("Waiting for payment"),
	FINISHED("Finished");

	OrderStatus(String displayName) {
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
