package com.example.demo.solid.goodexample;

import com.example.demo.solid.badexample.client.PurchaseDTO;

public interface PurchaseDecorator {

	PurchaseDTO decorate(PurchaseDTO purchase);

}
