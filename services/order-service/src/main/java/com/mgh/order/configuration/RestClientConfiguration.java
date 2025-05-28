package com.mgh.order.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
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
		RestClient restClient = RestClient.builder().baseUrl(customerServiceUrl)
				.requestFactory(getClientRequestFactory()).build();
		var restClientAdapter = RestClientAdapter.create(restClient);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(CustomerClient.class);
	}

	@Bean
	PaymentClient paymentClient() {
		RestClient restClient = RestClient.builder().baseUrl(paymentServiceUrl)
				.requestFactory(getClientRequestFactory()).build();
		var restClientAdapter = RestClientAdapter.create(restClient);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(PaymentClient.class);
	}
	
	@Bean
	ProductClient productClient() {
		RestClient restClient = RestClient.builder().baseUrl(productUrl)
				.requestFactory(getClientRequestFactory()).build();
		var restClientAdapter = RestClientAdapter.create(restClient);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(ProductClient.class);
	}

	private ClientHttpRequestFactory getClientRequestFactory() {
		ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.defaults()
				.withConnectTimeout(Duration.ofSeconds(3)).withReadTimeout(Duration.ofSeconds(3));
		return ClientHttpRequestFactoryBuilder.simple().build(clientHttpRequestFactorySettings);
	}
}