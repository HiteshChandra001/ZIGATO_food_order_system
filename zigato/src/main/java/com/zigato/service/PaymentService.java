package com.zigato.service;

import com.stripe.exception.StripeException;
import com.zigato.model.Orders;
import com.zigato.response.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse createPaymentLink(Orders order) throws StripeException ;
	

}
