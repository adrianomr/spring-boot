package com.example.demo.solid.goodexample.decorators;

import org.springframework.stereotype.Service;

import com.example.demo.solid.badexample.client.PurchaseDTO;
import com.example.demo.solid.badexample.client.UserClient;
import com.example.demo.solid.goodexample.PurchaseDecorator;

@Service
public class FindUserDecorator implements PurchaseDecorator {

	private final UserClient userClient;

	public FindUserDecorator(UserClient userClient) {
		this.userClient = userClient;
	}

	@Override public PurchaseDTO decorate(PurchaseDTO purchase) {
		var user = userClient.findUser( purchase.getUserId() );
		purchase.setUser( user );
		return purchase;
	}
}
