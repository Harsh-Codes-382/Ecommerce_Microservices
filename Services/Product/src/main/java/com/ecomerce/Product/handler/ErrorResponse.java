package com.ecomerce.Product.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) { }
