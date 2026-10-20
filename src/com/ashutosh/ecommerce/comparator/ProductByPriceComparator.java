package com.ashutosh.ecommerce.comparator;

import java.util.Comparator;

import com.ashutosh.ecommerce.model.Product;

public class ProductByPriceComparator implements Comparator<Product>{
	
	@Override
	public int compare(Product p1, Product p2) {
		return Double.compare(p1.getPrice(), p2.getPrice());
	}

}
