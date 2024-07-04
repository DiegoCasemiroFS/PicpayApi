package br.com.DiegoCasemiroFS.PicpayApi.dtos;

import br.com.DiegoCasemiroFS.PicpayApi.entity.enums.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
