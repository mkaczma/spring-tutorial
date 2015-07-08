package com.acme.order.delivery;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.acme.order.Customer;
import com.acme.order.delivery.strategy.DeliveryTimeStrategy;
import com.acme.order.delivery.strategy.PizzaTypeDeliveryTimeStrategy;
import com.acme.order.pizza.PizzaType;

import lombok.Getter;
import lombok.Setter;

public class BasicDeliveryTimeServiceImpl implements DeliveryTimeService {

	@Setter
	@Getter
	private TimeService timeService;
	@Setter
	@Getter
	private DeliveryTimeStrategy strategy;

	public BasicDeliveryTimeServiceImpl() {
	}

	public BasicDeliveryTimeServiceImpl(TimeService timeService) {
		this.timeService = timeService;
		this.strategy = new PizzaTypeDeliveryTimeStrategy();
	}

	@Override
	public Date getTime(Customer customer, PizzaType type) {

		int minutes = strategy.provideDeliveryMinutesOffset(customer, type);

		Date now = timeService.now();

		return DateUtils.addMinutes(now, minutes);
	}
}
