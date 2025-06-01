package com.mgh.order.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.mgh.order.customer.CustomerClient;
import com.mgh.order.payment.PaymentClient;
import com.mgh.order.product.ProductClient;

@Configuration
public class RestClientConfiguration {

	@Value("${application.config.customer-url}")
	private String customerServiceUrl;

	@Value("${application.config.payment-url}")
	private String paymentServiceUrl;

	@Value("${application.config.product-url}")
	private String productUrl;

	@Bean
	CustomerClient customerClient() {
		RestClient.Builder restClientBuilder = RestClient.builder().baseUrl(customerServiceUrl)
				.requestFactory(getClientRequestFactory());

		restClientBuilder = addAuthHeader(restClientBuilder);

		RestClient restClient = restClientBuilder.build();
		var restClientAdapter = RestClientAdapter.create(restClient);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(CustomerClient.class);
	}

	@Bean
	PaymentClient paymentClient() {
		RestClient.Builder restClientBuilder = RestClient.builder().baseUrl(paymentServiceUrl)
				.requestFactory(getClientRequestFactory());

		restClientBuilder = addAuthHeader(restClientBuilder);

		RestClient restClient = restClientBuilder.build();
		var restClientAdapter = RestClientAdapter.create(restClient);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(PaymentClient.class);
	}

	@Bean
	ProductClient productClient() {
		RestClient.Builder restClientBuilder = RestClient.builder().baseUrl(productUrl)
				.requestFactory(getClientRequestFactory());

		restClientBuilder = addAuthHeader(restClientBuilder);

		RestClient restClient = restClientBuilder.build();
		var restClientAdapter = RestClientAdapter.create(restClient);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(ProductClient.class);
	}

	private ClientHttpRequestFactory getClientRequestFactory() {
		ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.defaults()
				.withConnectTimeout(Duration.ofSeconds(3)).withReadTimeout(Duration.ofSeconds(3));
		return ClientHttpRequestFactoryBuilder.simple().build(clientHttpRequestFactorySettings);
	}

	private String getCurrentJwtToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof JwtAuthenticationToken) {
			JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;
			Jwt jwt = jwtAuthToken.getToken();
			return jwt.getTokenValue();
		}
		return null;
	}

	private RestClient.Builder addAuthHeader(RestClient.Builder builder) {
		String token = getCurrentJwtToken();
		if (token != null) {
			return builder.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		}
		return builder;
	}
}
