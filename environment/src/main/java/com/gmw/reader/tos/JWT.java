package com.gmw.reader.tos;

public record JWT(String secret, Long tokenExpiration, Long passwordTokenExpiration) {
}
