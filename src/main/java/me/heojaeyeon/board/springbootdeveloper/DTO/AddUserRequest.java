package me.heojaeyeon.board.springbootdeveloper.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;

}
