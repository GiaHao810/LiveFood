package app.manager.client.auth;

import lombok.Builder;

@Builder
public record RegisterRequest(String email, String password, String fullname) {
}
