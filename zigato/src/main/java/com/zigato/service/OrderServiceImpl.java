package com.zigato.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigato.model.Address;
import com.zigato.model.Cart;
import com.zigato.model.CartItem;
import com.zigato.model.OrderItem;
import com.zigato.model.Orders;
import com.zigato.model.Restaurent;
import com.zigato.model.STATUS;
import com.zigato.model.User;
import com.zigato.repository.AddressRepo;
import com.zigato.repository.OrderItemRepo;
import com.zigato.repository.OrderRepo;
import com.zigato.repository.UserRepo;
import com.zigato.request.OrderReq;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private OrderItemRepo itemRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RestaurentService restaurentService;
	@Autowired
	private CartService cartService;

	@Override
	public Orders createOrder(OrderReq req, User user) {
		Address address = addressRepo.save(req.getDeliveryAddress());

		if (!user.getAdresses().contains(address)) {
			user.getAdresses().add(address);
			userRepo.save(user);
		}

		Restaurent restaurent = restaurentService.findRestaurentById(req.getRestId());

		Orders order = new Orders();
		order.setCustomer(user);
		order.setDeliveryAddress(address);
		order.setCreatedAt(new Date());
		order.setOrderStatus(STATUS.PENDING);
		order.setRestaurent(restaurent);

		Cart cart = cartService.findCartByUserId(user.getId());

		List<OrderItem> orderitems = new ArrayList<>();

		for (CartItem item : cart.getItem()) {
			OrderItem oi = new OrderItem();
			oi.setFood(item.getFood());
			oi.setQuantity(item.getQuantity());
			oi.setTotalPrice(item.getTotalPrice());
			oi.setIngredients(item.getIngredients());
			oi.setTotalPrice(item.getTotalPrice());

			OrderItem savedOI = itemRepo.save(oi);
			orderitems.add(savedOI);
		}

		long total = cartService.calculateCartTotal(cart);
		order.setItems(orderitems);
		order.setTotalAmount(total);

		Orders savedOrder = orderRepo.save(order);

		restaurent.getOrders().add(savedOrder);

		return savedOrder;
	}

	@Override
	public Orders updateOrder(long oid, String orderStatus) {
		Orders order = orderRepo.findById(oid).orElseThrow(() -> new RuntimeException("invalid order id"));
		order.setOrderStatus(STATUS.valueOf(orderStatus));
		return orderRepo.save(order);
	}

	@Override
	public void cancelOrder(long oid) {
		Orders order = orderRepo.findById(oid).orElseThrow(() -> new RuntimeException("invalid order id"));
		orderRepo.delete(order);
	}

	@Override
	public List<Orders> getUserOrder(long uid) {
		return orderRepo.findByCustomerId(uid);
	}

	@Override
	public List<Orders> getRestOrders(long rid, String orderStatus) {
		List<Orders> orders = orderRepo.findByRestaurentId(rid);

		if (orderStatus != null) {
			orders = orders.stream().filter(o -> o.getOrderStatus().name().equals(orderStatus))
					.collect(Collectors.toList());
		}

		return orders;

	}

	@Override
	public Orders findOrderById(long oid) {
		return orderRepo.findById(oid).orElseThrow(() -> new RuntimeException("invaliid orderid"));
	}

}
