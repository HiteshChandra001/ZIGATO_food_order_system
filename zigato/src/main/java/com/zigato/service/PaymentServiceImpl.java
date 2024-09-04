package com.zigato.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.zigato.model.Orders;
import com.zigato.response.PaymentResponse;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Value("${stripe.api.key}")
	private String SECRET_KEY;

	@Override
	public PaymentResponse createPaymentLink(Orders order) throws StripeException {

		Stripe.apiKey = SECRET_KEY;

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("http://localhost:3000/payment/success" + order.getId())
				.setCancelUrl("http://localhost:3000/payment/fail")
				.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(1L)
						.setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
								.setUnitAmount((long) order.getTotalAmount())
								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName("zigato food").build())
								.build())
						.build())

				.build();

		Session session = Session.create(params);

		PaymentResponse res = new PaymentResponse();

		res.setPaymentUrl(session.getUrl());

		return res;
	}

}
