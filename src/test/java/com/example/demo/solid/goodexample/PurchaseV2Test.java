package com.example.demo.solid.goodexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.AnswerWithDelay;
import com.example.demo.solid.badexample.Purchase;
import com.example.demo.solid.badexample.client.DiscountClient;
import com.example.demo.solid.badexample.client.PlanClient;
import com.example.demo.solid.badexample.client.PurchaseClient;
import com.example.demo.solid.badexample.client.PurchaseDTO;
import com.example.demo.solid.badexample.client.UserClient;
import com.example.demo.solid.badexample.entities.User;

@SpringBootTest
class PurchaseV2Test {

	@MockBean
	PlanClient planClient;
	@MockBean
	UserClient userClient;
	@MockBean
	DiscountClient discountClient;
	@MockBean
	PurchaseClient purchaseClient;
	@Autowired
	PurchaseV2 purchase;
	@Captor ArgumentCaptor<PurchaseDTO> purchaseCaptor;

	@BeforeEach
	void setUp() {
		Mockito.doAnswer( new AnswerWithDelay( new User() ) )
				.when( userClient ).findUser( 1L );
		Mockito.doAnswer( new AnswerWithDelay( 30d ) )
				.when( discountClient ).findDiscount( 1L );
		Mockito.doAnswer( new AnswerWithDelay( 100d ) )
				.when( planClient ).findPlan( "PLAN" );
	}

	@Test
	void executeShouldCreatePurchase() {
		purchase.execute( 1L, "PLAN" );


		verify(purchaseClient)
				.purchase( purchaseCaptor.capture() );

		var purchase = purchaseCaptor.getValue();

		assertEquals( 100d, purchase.getPrice() );
		assertEquals( 30d, purchase.getDiscount() );
		assertNotNull( purchase.getUser() );
	}

	@Test
	void executeShouldRunUnder3Seconds() {

		assertTimeout(
				Duration.ofMillis(3000),
				() -> purchase.execute( 1L, "PLAN" ));

		verify(purchaseClient)
				.purchase( purchaseCaptor.capture() );

	}
}