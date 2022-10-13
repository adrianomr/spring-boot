package com.example.demo.solid.goodexample;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.solid.badexample.client.PurchaseClient;
import com.example.demo.solid.badexample.client.PurchaseDTO;

@Service
public class PurchaseV2 {
	private final List<PurchaseDecorator> decorators;
	private final PurchaseClient purchaseClient;

	public PurchaseV2(List<PurchaseDecorator> decorators,
			PurchaseClient purchaseClient) {
		this.decorators = decorators;
		this.purchaseClient = purchaseClient;
	}

	public void execute(Long userId, String plan) {
		var purchase = new PurchaseDTO();
		purchase.setUserId( userId );
		purchase.setPlan( plan );

		decorators.parallelStream()
				.forEach( purchaseDecorator -> purchaseDecorator.decorate( purchase ) );

		purchaseClient.purchase( purchase );
	}

}
