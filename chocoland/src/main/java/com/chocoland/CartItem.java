package com.chocoland;

public class CartItem {
    private Product product;
	private int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

// public getter methods are necessary for the jstl tags to be able to access the value
	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}


}
