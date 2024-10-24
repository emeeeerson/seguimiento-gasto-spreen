package com.gastos.seguimiento.controller.auth;
import java.util.Date;


public record TokenDto(
        String token,
        Date expirationDate
) {

}

