package com.example.demo.solid.goodexample.decorators;

import org.springframework.stereotype.Service;

import com.example.demo.solid.badexample.client.PlanClient;
import com.example.demo.solid.badexample.client.PurchaseDTO;
import com.example.demo.solid.goodexample.PurchaseDecorator;

@Service
public class FindPlanDecorator implements PurchaseDecorator {

	private final PlanClient planClient;

	public FindPlanDecorator(PlanClient planClient) {
		this.planClient = planClient;
	}

	@Override public PurchaseDTO decorate(PurchaseDTO purchase) {
		var planPrice = planClient.findPlan( purchase.getPlan() );
		purchase.setPrice( planPrice );
		return purchase;
	}
}
