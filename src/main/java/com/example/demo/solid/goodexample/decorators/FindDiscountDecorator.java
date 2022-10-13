package com.example.demo.solid.goodexample.decorators;

import org.springframework.stereotype.Service;

import com.example.demo.solid.badexample.client.DiscountClient;
import com.example.demo.solid.badexample.client.PurchaseDTO;
import com.example.demo.solid.goodexample.PurchaseDecorator;

@Service
public class FindDiscountDecorator implements PurchaseDecorator {

	private final DiscountClient discountClient;

	public FindDiscountDecorator(DiscountClient discountClient) {
		this.discountClient = discountClient;
	}

	@Override public PurchaseDTO decorate(PurchaseDTO purchase) {
		var discount = discountClient.findDiscount( purchase.getUserId() );
		purchase.setDiscount( discount );
		return purchase;
	}
}
