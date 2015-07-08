package com.acme.order.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleMessageTemplateService implements MessageTemplateService {

	private DeliveryTemplate deliveryTemplate;
	private OrderCancelledTemplate cancelDeliveryTemplate;

	public SimpleMessageTemplateService() {
		this.deliveryTemplate = new DeliveryTemplate();
		this.cancelDeliveryTemplate = new OrderCancelledTemplate();
	}

	@Override
	public DeliveryTemplate getDeliveryTemplate() {
		return deliveryTemplate;
	}

	@Override
	public OrderCancelledTemplate getCancelTemplate() {
		return cancelDeliveryTemplate;
	}

}
