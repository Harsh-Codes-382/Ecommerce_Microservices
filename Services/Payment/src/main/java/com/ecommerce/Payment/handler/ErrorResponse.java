package com.ecommerce.Payment.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errorss
) {
}
