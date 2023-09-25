package com.westernhills.westernhills.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RazorpayConfig {

    @Value("${razorpay.apiKey}")
    private String apiKey;

    @Value("${razorpay.apiSecret}")
    private String apiSecret;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(apiKey,apiSecret);
    }




}
