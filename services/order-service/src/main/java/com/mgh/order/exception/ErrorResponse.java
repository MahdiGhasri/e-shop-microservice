package com.mgh.order.exception;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {

}