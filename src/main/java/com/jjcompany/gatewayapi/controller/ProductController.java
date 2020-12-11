package com.jjcompany.gatewayapi.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
public class ProductController {
	
	private static final String BASE_URL = "https://product-api-jj.herokuapp.com";
	private OkHttpClient client;
	
	public ProductController() {
		this.client = new OkHttpClient();
	}
	
	
	@GetMapping("/product")
	public String all() throws IOException{
		Request request = new Request.Builder().url(BASE_URL + "/product").build();

		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@PostMapping("/product")
	public String newProduct(@org.springframework.web.bind.annotation.RequestBody String newProduct) throws IOException {
		RequestBody body = RequestBody
				.create(newProduct, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/product")
				.post(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@GetMapping("/product/{id}")
	public String one(@PathVariable Long id) throws IOException {
		Request request = new Request.Builder().url(BASE_URL + "/product/" + id).build();
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	
	@PutMapping("/product/{id}")
	public String replaceProduct(@org.springframework.web.bind.annotation.RequestBody  String newProduct, @PathVariable Long id) throws IOException {
		RequestBody body = RequestBody
				.create(newProduct, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/product/" + id)
				.put(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@DeleteMapping("/product/{id}")
	void deleteProduct(@PathVariable Long id) throws IOException {
		Request request = new Request.Builder().url(BASE_URL + "/product/" + id).delete().build();
		Call call = client.newCall(request);
		Response response = call.execute();
	}
	
	
}
