package de.is.project.shop.impl.domain;

public enum RequestStatus {

	IN_PROCESS("In Process"),
    ACCEPTED_BY_ADMIN("Accepted by Admin"),
    WAITING_FOR_REQUESTER("Waiting for requester"),
    DACCEPTED_BY_USER("Accepted by user"),
	WAITING_FOR_ADMIN("Waiting for admin"),
	FINISHED("Finished");

	RequestStatus(String displayName) {
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
