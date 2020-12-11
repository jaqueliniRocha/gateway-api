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
public class BrandController {
	
	private static final String BASE_URL = "https://product-api-jj.herokuapp.com";
	private OkHttpClient client;
	
	public BrandController() {
		this.client = new OkHttpClient();
	}
	

	@GetMapping("/brand")
	public String all() throws IOException{
		
		Request request = new Request.Builder().url(BASE_URL + "/brand").build();

		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	
	@PostMapping("/brand")
	public String newBrand(@org.springframework.web.bind.annotation.RequestBody String newBrand) throws IOException {
		RequestBody body = RequestBody
				.create(newBrand, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/brand")
				.post(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@GetMapping("/brand/{id}")
	public String one(@PathVariable Long id) throws IOException {
		Request request = new Request.Builder().url(BASE_URL + "/brand/" + id).build();
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	
	@PutMapping("/brand/{id}")
	public String replaceBrand(@org.springframework.web.bind.annotation.RequestBody  String newBrand, @PathVariable Long id) throws IOException {
		RequestBody body = RequestBody
				.create(newBrand, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/brand/" + id)
				.put(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@DeleteMapping("/brand/{id}")
	void deleteBrand(@PathVariable Long id) throws IOException {
		Request request = new Request.Builder().url(BASE_URL + "/brand/" + id).delete().build();
		Call call = client.newCall(request);
		Response response = call.execute();
	}
	
}
