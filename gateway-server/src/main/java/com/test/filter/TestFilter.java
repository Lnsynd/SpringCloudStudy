package com.test.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component   //需要注册为Bean
public class TestFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {   //只需要实现此方法
        ServerHttpRequest request = exchange.getRequest();

        System.out.println(request.getQueryParams());

        List<String> list = request.getQueryParams().get("test");


        // 如果含有test的参数
        if (list != null && list.contains("1")) {
            // 放行
            return chain.filter(exchange);
        } else {
            // 在此处停下
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0; // 0 代表过滤器优先级
    }
}
