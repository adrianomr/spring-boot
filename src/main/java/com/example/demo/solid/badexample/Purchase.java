package com.example.demo.solid.badexample;

import org.springframework.stereotype.Service;

import com.example.demo.solid.badexample.client.DiscountClient;
import com.example.demo.solid.badexample.client.PlanClient;
import com.example.demo.solid.badexample.client.PurchaseClient;
import com.example.demo.solid.badexample.client.PurchaseDTO;
import com.example.demo.solid.badexample.client.UserClient;

@Service
public class Purchase {

	private final DiscountClient discountClient;
	private final PlanClient planClient;
	private final UserClient userClient;
	private final PurchaseClient purchaseClient;

	public Purchase(DiscountClient discountClient, PlanClient planClient,
			UserClient userClient, PurchaseClient purchaseClient) {
		this.discountClient = discountClient;
		this.planClient = planClient;
		this.userClient = userClient;
		this.purchaseClient = purchaseClient;
	}

	public void execute(Long userId, String plan){
		var user = userClient.findUser( userId );
		var discount = discountClient.findDiscount( userId );
		var planPrice = planClient.findPlan( plan );

		var purchase = new PurchaseDTO();
		purchase.setPrice( planPrice );
		purchase.setUser( user );
		purchase.setDiscount( discount );

		purchaseClient.purchase( purchase );
	}

}
