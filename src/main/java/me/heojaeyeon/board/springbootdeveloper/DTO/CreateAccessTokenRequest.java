package me.heojaeyeon.board.springbootdeveloper.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequest {
    private String refreshToken;
}
