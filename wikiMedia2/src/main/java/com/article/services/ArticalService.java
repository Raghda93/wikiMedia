package main.java.com.article.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class ArticalService {

	/**
	 * Calls Wikipedia API and retrieve articles that contain ( Amman or Jordan ).
	 * @return
	 */
	public static Response invoke() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget webTarget = createWebTarget(client);
		Invocation.Builder requestBuilder = createRequestBuilder(webTarget);
		
		Response response = sendRequest(requestBuilder, HttpMethod.GET);
		return response;
	}
    
    
    /**
     * createWebTarget
     * @param client
     * @return
     */
    private static WebTarget createWebTarget(Client client) {
		return client.target(getUriBuilder());
	}
    
    /**
     * Creates UriBuilder
     * @return
     */
    private static UriBuilder getUriBuilder() {
    	
		UriBuilder uriBuilder = UriBuilder.fromPath("https://en.wikipedia.org/w/api.php");
		Map<String, List<String>> queryParameters = new HashMap<>();
		
		List<String> list = new ArrayList<>();
		list.add("query");
		queryParameters.put("action", list);
		
		list = new ArrayList<>();
		list.add("search");
		queryParameters.put("list", list);
		
		list = new ArrayList<>();
		list.add("");
		queryParameters.put("utf8", list);
		
		list = new ArrayList<>();
		list.add("Amman");
		list.add("Jordan");
		queryParameters.put("srsearch", list);
		
		list = new ArrayList<>();
		list.add("max");
		queryParameters.put("ailimit", list);
		
		list = new ArrayList<>();
		list.add("json");
		queryParameters.put("format", list);
		
		applyQueryParameters(uriBuilder, queryParameters);
		return uriBuilder;
	}
    
    
    /**
     * Used to add a query parameters to UriBuilder.
     * @param uriBuilder
     * @param queryParameters
     */
    private static void applyQueryParameters(UriBuilder uriBuilder, Map<String, List<String>> queryParameters) {
		if (queryParameters.isEmpty()) {
			return;
		}
		for (Entry<String, List<String>> entry : queryParameters.entrySet()) {
			String paramName = entry.getKey();
			List<String> values = entry.getValue();
			if (values == null || values.isEmpty()) {
				continue;
			}
			uriBuilder.queryParam(paramName, values.toArray());
		}
	}
    
    private static Invocation.Builder createRequestBuilder(WebTarget webTarget) {
  		return webTarget.request();
  	}
      
      
      private static Response sendRequest(Builder requestBuilder, String httpMethod) {
  		return requestBuilder.method(httpMethod);
  	}
      
}
