package fii.hotel.manager.security.dto;

import java.io.Serializable;

public class AuthenticationResponseDto implements Serializable {

    private final String token;

    public AuthenticationResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
