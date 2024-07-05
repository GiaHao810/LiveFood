package app.manager.client.dto.request;

import lombok.Builder;

@Builder
public record RegisterRequest(String mail, String username, String password) {
}
