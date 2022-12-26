package com.punith.Product;

import org.springframework.stereotype.Component;

@Component
public class ProductUtility {
	
	public float calculateGST(float originalPrice)
	{
		return (originalPrice*12)/100;
		
	}
	
	public float calculateDiscount(float originalPrice)
	{
		return (originalPrice*5)/100;
	}
	
}
