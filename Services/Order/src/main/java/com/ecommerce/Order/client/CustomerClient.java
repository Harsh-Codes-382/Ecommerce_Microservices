package com.ecommerce.Order.client;

import com.ecommerce.Order.model.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        // Application name of service to which we want to connect
        name = "customer-service"
//        url = "${application.config.customer-url}"

/*      This url is coming from application.yml & only needed
        If we want to make call via API gateway or direct to service
        But since we have service discovery we don't need to give this url & feign will call to eureka server
        So, eureka will give list of services & this feign will load Balance the request default is "RoundRobin"
*/
)
public interface CustomerClient {

    @GetMapping("find/{customerId}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customerId") String customerId);
}
