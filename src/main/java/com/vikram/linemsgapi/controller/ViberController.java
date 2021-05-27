package com.vikram.linemsgapi.controller;

import java.net.URI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ViberController {

	@RequestMapping(value = "/vibertest")
	public String getSmoochiapi() {

		return "";
	}

	@RequestMapping(value = "/viberwebhook", method = RequestMethod.POST)
	public String smoochMessagesPostResponse(@RequestBody String body) {

		System.out.println(body);

		String send_message_url = "https://chatapi.viber.com/pa/send_message";

		try {
			// JSONParser reads the data from string object and break each data
			// into key value pairs
			JSONParser parse = new JSONParser();
			// Type caste the parsed json data in json object
			JSONObject jobj = (JSONObject) parse.parse(body);

			JSONObject jsonobj_1 = (JSONObject) jobj.get("sender");
			String senderid = (String) jsonobj_1.get("id");

			JSONObject jsonobj_2 = (JSONObject) jobj.get("message");
			String text = (String) jsonobj_2.get("text");

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("X-Viber-Auth-Token", "4d4ec1b9f867d0f2-ce22aff66510a7a8-77db4313bbf66fb6");

			JSONObject senderObject = new JSONObject();
			senderObject.put("name", "Vikram Sender");
			senderObject.put("avatar",
					"https://share.cdn.viber.com/pg_download?id=0-04-01-eeeae57ec8a4ab68edf20e7adb2bc89f85f01ee54a43c90e04247f17d44b509b&filetype=jpg&type=icon");

			JSONObject requestJsonObject = new JSONObject();
			requestJsonObject.put("receiver", senderid);
			requestJsonObject.put("min_api_version", 1);
			requestJsonObject.put("tracking_data", "tracking data");
			requestJsonObject.put("type", "text");
			requestJsonObject.put("sender", senderObject);
			requestJsonObject.put("text", "Thanks for your message to our Viber bot. <" + text
					+ ">. This is standalone Java microservice response");

			if ("pt".equalsIgnoreCase(text)) {
				JSONObject keyboardObject = new JSONObject();
				keyboardObject.put("DefaultHeight", true);
				keyboardObject.put("BgColor","#FFFFFF");
				
				JSONArray buttonArray = new JSONArray();
				
				JSONObject firstButtonObject = new JSONObject();
				firstButtonObject.put("Columns", 6);
				firstButtonObject.put("Rows", 1);
				firstButtonObject.put("BgColor", "#2db9b9");
				firstButtonObject.put("BgMediaType", "gif");
				firstButtonObject.put("BgMedia", "http://www.url.by/test.gif");
				firstButtonObject.put("BgLoop", true);
				firstButtonObject.put("ActionType", "open-url");
				firstButtonObject.put("ActionBody", "www.tut.by");
				firstButtonObject.put("Image", "www.tut.by/img.jpg");
				firstButtonObject.put("Text", "Key text");
				firstButtonObject.put("TextVAlign", "middle");
				firstButtonObject.put("TextHAlign", "center");
				firstButtonObject.put("TextOpacity", 60);
				firstButtonObject.put("TextSize", "regular");
				
				buttonArray.add(firstButtonObject);
				
				keyboardObject.put("Buttons", buttonArray);
				
				requestJsonObject.put("keyboard", keyboardObject);

			}
			HttpEntity<String> request = new HttpEntity<String>(requestJsonObject.toString(), headers);
			URI locationHeader = restTemplate.postForLocation(send_message_url, request);

			System.out.println("locationHeader in viber webhook api");
			System.out.println(locationHeader);

		} catch (Exception e) {

		}

		return "Webhook working";
	}

}
