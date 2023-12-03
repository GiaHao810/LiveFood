package app.manager.client.auth;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String email, String password) {
}
