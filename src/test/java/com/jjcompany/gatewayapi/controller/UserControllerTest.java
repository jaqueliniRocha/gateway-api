package com.jjcompany.gatewayapi.controller;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class UserControllerTest {
	
    private static final String BASE_URL = "https://user-api-jj.herokuapp.com";


	@Test
	void test() throws IOException {
		OkHttpClient client = new OkHttpClient();;

		Request request = new Request.Builder().url(BASE_URL + "/user").build();

		Call call = client.newCall(request);
		Response response = call.execute();
		
		System.out.println(response.body().string());
	}

}
