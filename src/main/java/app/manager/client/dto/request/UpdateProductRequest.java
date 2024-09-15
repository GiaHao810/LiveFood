package app.manager.client.dto.request;

public record UpdateProductRequest(String code, String name, Double price, String category) {
}
