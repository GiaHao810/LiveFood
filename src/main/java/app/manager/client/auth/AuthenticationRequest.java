package app.manager.client.auth;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String username, String password) {
}
