package app.manager.client.dto.request;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String username, String password) {
}
