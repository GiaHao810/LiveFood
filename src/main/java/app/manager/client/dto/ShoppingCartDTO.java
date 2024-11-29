package app.manager.client.dto;

import app.manager.client.entity.CartItem;

import java.util.List;

public record ShoppingCartDTO(Double quantity, List<CartItem> cartItems) {
}
