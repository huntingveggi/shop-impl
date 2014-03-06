package de.is.project.shop.impl.domain;

public enum Role {

	CUSTOMER_ROLE("ROLE_CUSTOMER"),
    ADMIN_ROLE("ROLE_ADMIN");

	Role(String displayName) {
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
