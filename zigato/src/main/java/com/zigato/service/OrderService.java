package com.zigato.service;

import java.util.List;

import com.zigato.model.Orders;
import com.zigato.model.User;
import com.zigato.request.OrderReq;

public interface OrderService {
	public Orders createOrder(OrderReq req, User user);
	
	public Orders updateOrder(long oid, String orderStatus);
	
	public void cancelOrder(long oid);
	
	public List<Orders> getUserOrder(long uid);
	
	public List<Orders> getRestOrders(long rid, String orderStatus);
	
	public Orders findOrderById(long oid);
	
}
