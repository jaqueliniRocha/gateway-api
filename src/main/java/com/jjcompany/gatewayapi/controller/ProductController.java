package com.jjcompany.gatewayapi.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jjcompany.gatewayapi.config.JwtTokenUtil;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class ProductController {
	
	private static final String BASE_URL = "https://product-api-jj.herokuapp.com";
	private OkHttpClient client;
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public ProductController() {
		this.client = new OkHttpClient();
	}
	
	@GetMapping("/product/expired")
	public ResponseEntity<String> findExpiredProducts(@RequestHeader(value="Authorization") String authorizationHeader) throws IOException{
		if(!jwtTokenUtil.isValidToken(authorizationHeader.replace("Bearer ", ""))) {
			return ResponseEntity.badRequest().build();
		}
		Request request = new Request.Builder().url("https://product-report-api-jj.herokuapp.com/product/expired").build();

		Call call = client.newCall(request);
		Response response = call.execute();
		
		return ResponseEntity.ok(response.body().string());
	}
	
	
	@GetMapping("/product")
	public ResponseEntity<String> all(@RequestHeader(value="Authorization") String authorizationHeader) throws IOException{
		if(!jwtTokenUtil.isValidToken(authorizationHeader.replace("Bearer ", ""))) {
			return ResponseEntity.badRequest().build();
		}
		Request request = new Request.Builder().url(BASE_URL + "/product").build();

		Call call = client.newCall(request);
		Response response = call.execute();
		
		return ResponseEntity.ok(response.body().string());
	}
	
	@PostMapping("/product")
	public ResponseEntity<String> newProduct(@org.springframework.web.bind.annotation.RequestBody String newProduct, @RequestHeader(value="Authorization") String authorizationHeader) throws IOException {
		if(!jwtTokenUtil.isValidToken(authorizationHeader.replace("Bearer ", ""))) {
			return ResponseEntity.badRequest().build();
		}
		RequestBody body = RequestBody
				.create(newProduct, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/product")
				.post(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return ResponseEntity.ok(response.body().string());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<String> one(@PathVariable Long id, @RequestHeader(value="Authorization") String authorizationHeader) throws IOException {
		if(!jwtTokenUtil.isValidToken(authorizationHeader.replace("Bearer ", ""))) {
			return ResponseEntity.badRequest().build();
		}
		Request request = new Request.Builder().url(BASE_URL + "/product/" + id).build();
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return ResponseEntity.ok(response.body().string());
	}
	
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> replaceProduct(@org.springframework.web.bind.annotation.RequestBody  String newProduct, @PathVariable Long id, @RequestHeader(value="Authorization") String authorizationHeader) throws IOException {
		if(!jwtTokenUtil.isValidToken(authorizationHeader.replace("Bearer ", ""))) {
			return ResponseEntity.badRequest().build();
		}
		RequestBody body = RequestBody
				.create(newProduct, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/product/" + id)
				.put(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return ResponseEntity.ok(response.body().string());
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id, @RequestHeader(value="Authorization") String authorizationHeader) throws IOException {
		if(!jwtTokenUtil.isValidToken(authorizationHeader.replace("Bearer ", ""))) {
			return ResponseEntity.badRequest().build();
		}
		Request request = new Request.Builder().url(BASE_URL + "/product/" + id).delete().build();
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return ResponseEntity.noContent().build();
	}
	
	
}
