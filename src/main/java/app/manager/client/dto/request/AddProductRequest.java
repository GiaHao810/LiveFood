package app.manager.client.dto.request;

import app.manager.client.model.Category;

public record AddProductRequest(String name, Double price, String category) {
}
