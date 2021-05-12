package com.vikram.linemsgapi.controller;

import java.net.URI;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KakaoMsgController {

	@RequestMapping(value = "/postdata", method = RequestMethod.POST)
	public String PostData() {

		return "{\n" + "   \"value\":\"4\",\n" + "   \"name\":\"David\"\n" + "}";
	}

	@RequestMapping(value = "/kakaooauth", method = RequestMethod.POST)
	public String kakaooauth(@RequestBody String body) {

		System.out.println("body in kakaooauth api");
		System.out.println(body);

		return "";
		//
		// String token = "";
		// RestTemplate restTemplate = new RestTemplate();
		//
		// HttpHeaders headers=new HttpHeaders();
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		//
		// //kakao
		// headers.setBearerAuth(token);
		//
		// HttpEntity<String> entity=new HttpEntity<String>(headers);
		//
		// //return
		// restTemplate.exchange("http://localhost:8080/postdata",HttpMethod.POST,entity,String.class).getBody();
		//
		//
		// return
		// restTemplate.exchange("https://kapi.kakao.com/v1/api/talk/profile",HttpMethod.GET,entity,String.class).getBody();
	}

	@RequestMapping(value = "/kakaotoken")
	public String kakaotoken() {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String authorisation_code_url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=e8cc98f63ab405693506084198130566&redirect_uri=none&prompt=none";

		JSONObject requestJsonObject = new JSONObject();
		HttpEntity<String> request1 = new HttpEntity<String>(requestJsonObject.toString(), headers);

		URI locationHeader1 = restTemplate.postForLocation(authorisation_code_url, request1);

		System.out.println("locationHeader in kakao auth code api");
		System.out.println(locationHeader1);

		String token_url = "https://kauth.kakao.com/oauth/token";

		//rJSONObject requestJsonObject = new JSONObject();
		requestJsonObject.put("grant_type", "authorization_code");
		requestJsonObject.put("client_id", "e8cc98f63ab405693506084198130566");
		requestJsonObject.put("code",
				"HgX-MBllWujA0UCMputwyCY1_VL_xbWibhaNGiya5V8yN-MueAllD3JOsS-KZ9JMvEzjQQo9cpgAAAF5RS7hJw");
//
//		HttpEntity<String> request = new HttpEntity<String>(requestJsonObject.toString(), headers);
//
//		URI locationHeader = restTemplate.postForLocation(token_url, request);
//
//		System.out.println("locationHeader in kakao token api");
//		System.out.println(locationHeader);
//
//		String resultAsJsonStr = restTemplate.postForObject(token_url, request, String.class);
//
//		System.out.println("resultAsJsonStr>>" + resultAsJsonStr);

		return "";
	}

}
