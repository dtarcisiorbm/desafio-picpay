package com.picpaysimplificado.dto;

import com.picpaysimplificado.core.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastname, String doucment, BigDecimal balance, String email, String password,
                      UserType userType) {
}
