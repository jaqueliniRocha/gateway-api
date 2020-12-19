package com.jjcompany.gatewayapi.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jjcompany.gatewayapi.config.JwtTokenUtil;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class ProductReportController {

	private static final String BASE_URL = "https://product-report-api-jj.herokuapp.com";
	private OkHttpClient client;
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public ProductReportController() {
		this.client = new OkHttpClient();
	}
	
	@GetMapping("/product/expired")
	public ResponseEntity<String> findExpiredProducts(@RequestHeader(value="Authorization") String authorizationHeader) throws IOException{
		if(!jwtTokenUtil.isValidToken(authorizationHeader.replace("Bearer ", ""))) {
			return ResponseEntity.badRequest().build();
		}
		Request request = new Request.Builder().url(BASE_URL + "/product/expired").build();

		Call call = client.newCall(request);
		Response response = call.execute();
		
		return ResponseEntity.ok(response.body().string());
	}
	
}
