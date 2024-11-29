package app.manager.client.dto;

import app.manager.client.entity.OrderItem;

import java.util.List;

public record OrderDTO(List<OrderItem> orderItems) {
}
