package com.ashutosh.ecommerce.comparator;

import java.util.Comparator;

import com.ashutosh.ecommerce.model.Product;

public class ProductByNameComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return p1.getProductName().compareToIgnoreCase(p2.getProductName());
	}
}
