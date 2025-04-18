package com.mgh.payment_domain.exception;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {

}