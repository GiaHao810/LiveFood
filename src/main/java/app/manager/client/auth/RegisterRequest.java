package app.manager.client.auth;

import lombok.Builder;

@Builder
public record RegisterRequest(String mail, String password, String fullname) {
}
