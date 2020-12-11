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
public class UserController {
	
    private static final String BASE_URL = "https://user-api-jj.herokuapp.com";
	private OkHttpClient client;
    

	public UserController() {
		this.client = new OkHttpClient();
	}


	@GetMapping("/user")
	public String all() throws IOException {
		Request request = new Request.Builder()
				.url(BASE_URL + "/user")
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@PostMapping("/user")
	public String newUser(@org.springframework.web.bind.annotation.RequestBody String newUser) throws IOException {
		RequestBody body = RequestBody
				.create(newUser, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/user")
				.post(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}

	
	@GetMapping("/user/{id}")
	public String one(@PathVariable Long id) throws IOException {
		Request request = new Request.Builder()
				.url(BASE_URL + "/user/" + id)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@PutMapping("/user/{id}")
	public String replaceUser(@org.springframework.web.bind.annotation.RequestBody String newUser, @PathVariable Long id) throws IOException {
		RequestBody body = RequestBody
				.create(newUser, MediaType.parse("application/json"));
		Request request = new Request.Builder()
				.url(BASE_URL + "/user/" + id)
				.put(body)
				.build();
		
		Call call = client.newCall(request);
		Response response = call.execute();
		
		return response.body().string();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Long id) throws IOException {
		Request request = new Request.Builder()
				.url(BASE_URL + "/user/" + id)
				.delete().build();
		Call call = client.newCall(request);
		Response response = call.execute();
		
	}

}
