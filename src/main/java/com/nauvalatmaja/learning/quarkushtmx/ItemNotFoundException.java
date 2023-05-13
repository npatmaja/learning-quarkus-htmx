package com.nauvalatmaja.learning.quarkushtmx;

public class ItemNotFoundException extends Exception {
	public ItemNotFoundException() {
		super("Item is not found");
	}

	public ItemNotFoundException(int id) {
		super("Item " + id + " is not found");
	}

}

