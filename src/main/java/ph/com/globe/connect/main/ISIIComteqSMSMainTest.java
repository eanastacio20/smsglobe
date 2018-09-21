/**
 * 
 * 
 * Free GLOBE SMS API - only out going messages are allowed.
 * 
 * GLOBE DOCS API : http://www.globelabs.com.ph/docs/
 * 
 * Prerequisites:
 * 
 * 	* Email Address - use as primary holder of the account
 *  * Sim Card Globe/Smart - for subscriptions confirmation code and received/forwarded sms 
 *  
 * 
 * @author emanastacio
 * @since 2018-09-20
 */
package ph.com.globe.connect.main;

import org.json.JSONObject;

import ph.com.globe.connect.ApiException;
import ph.com.globe.connect.Authentication;
import ph.com.globe.connect.HttpRequestException;
import ph.com.globe.connect.HttpResponseException;
import ph.com.globe.connect.Sms;

public class ISIIComteqSMSMainTest {
	
	public static String APP_ID = "45a4C5qxGEu67iayj5cxxzu8o5zGCzdB";
	public static String APP_SECRECT = "ac57b6ba26131f33c6e4e5f1f0dfbaad84506978f7b20354fe8e97be3392609e";
	// Globe code
	public static String ACCESS_TOKEN_CODE = "KAUGRagksMxexLIMRaqyf5b6BKubkMpzhnoqd6s8Lo9pFq6krkuLME96UxzXr4Sb6EjpskjojGSMn754fjdjK4SLE95ESdo87KC7xnRjspKLa7u8KpKzHRaMMeCb5cp5GLAcMnGC8kpz8H9kLbxu65ngpseX8eqCj99MkSMdjg9SGx7kpfj5oLMSbyEebsrkXkRSrAEE6Uq6kobuGnorLFdgqyRsgrMqBhx66RruBMaB7fzEeMnIbeanMsAkK5gU";

	// Sun cross telco code
//	public static String ACCESS_TOKEN_CODE = "KMFznae5HpaMMoFqo5o6hxroK4s9XGrLH75LxnFGxpozUzXe6ASrgzxahK5ko5C5yqkquRLEbEUpxB96IELz95u9jb8dfRM8jzFMGqnAsqX87ASLry5xfz4A76CG5cjEakAcAXnCGzygbfqo88nSxXqebsLn8X5FGoberf5gzkKueGBXRIbGEAeUebqz9u5ekK4C7EzRkhyXepbSB8pj7UGXLGeFpRGM6HAkoLXs7E5BphGLMynFAxaRzHapGpaF";

	public static void main(String[] args) {
		ISIIComteqSMSMainTest client = new ISIIComteqSMSMainTest();
		String token = client.getToken();
		System.out.println(String.format("TOKEN %s",token));
		String shortCode = "3505";
		Sms sms = new Sms(shortCode, token);
		JSONObject response;
		try {
			response = sms
			    .setClientCorrelator(shortCode)
			    .setReceiverAddress("09669306141") // Globe 
			    .setMessage("Hello World SMS Test from Java Again!")
			    .sendMessage()
			    .getJsonResponse();
			System.out.println(response);
		} catch (HttpResponseException | ApiException | HttpRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public String getToken() {
		
		JSONObject response;
		String token = null;
		try {
			Authentication auth = new Authentication(ISIIComteqSMSMainTest.APP_ID, ISIIComteqSMSMainTest.APP_SECRECT);
			String dialogUrl = auth.getDialogUrl();
			// redirect the user, process the code then ...
			response = auth
			    .getAccessToken(ISIIComteqSMSMainTest.ACCESS_TOKEN_CODE)
			    .getJsonResponse();
			System.out.println(dialogUrl);
			System.out.println(response);
			System.out.println(response.get("access_token"));
			token = (String) response.get("access_token");
		} catch (HttpResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}

}
