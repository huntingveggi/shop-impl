package de.is.project.shop.impl.domain;

public enum PaymentTerm {
	
	CREDITCARD("Creditcard"),
    DEBIT_ADVICE("Debit Advice"),
    ON_ACCOUNT("On Account"),
	BANK_TRANSFER("Bank Transfer");

	PaymentTerm(String displayName) {
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
