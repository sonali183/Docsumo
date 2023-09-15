import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpBinApiTests {

    private final String BASE_URL = "https://httpbin.org";
    private final HttpClient httpClient = HttpClients.createDefault();

    @Test
    public void testImageExtension() throws IOException {
        // Make a GET request to /images endpoint
        HttpGet request = new HttpGet(BASE_URL + "/images");
        request.addHeader("Accept", "image/png"); // Replace with the desired image format
        HttpResponse response = httpClient.execute(request);

        // Verify the response status code
        assertEquals(200, response.getStatusLine().getStatusCode());

        // Get the response content type header
        String contentType = response.getFirstHeader("Content-Type").getValue();

        // Extract the file extension from the content type
        String fileExtension = contentType.substring(contentType.lastIndexOf("/") + 1);

        // Verify if the response image's extension matches the Accept header
        assertEquals("png", fileExtension);
    }

    @Test
    public void testCookies() throws IOException {
        // Using /cookies/set endpoint to set cookies
        HttpGet setCookiesRequest = new HttpGet(BASE_URL + "/cookies/set?cookie1=value1&cookie2=value2");
        httpClient.execute(setCookiesRequest);

        // Using /cookies endpoint to retrieve cookies
        HttpGet getCookiesRequest = new HttpGet(BASE_URL + "/cookies");
        HttpResponse response = httpClient.execute(getCookiesRequest);

        // Verify the response status code
        assertEquals(200, response.getStatusLine().getStatusCode());

        // Parse the response content to extract cookies
        HttpEntity entity = response.getEntity();
        String cookies = EntityUtils.toString(entity);

        // Verify if the cookies are properly set
        assertTrue(cookies.contains("cookie1=value1"));
        assertTrue(cookies.contains("cookie2=value2"));
    }

    @Test
    public void testDeleteCookie() throws IOException {
        // Using /cookies/set endpoint to set cookies
        HttpGet setCookiesRequest = new HttpGet(BASE_URL + "/cookies/set?cookieToDelete=valueToDelete");
        httpClient.execute(setCookiesRequest);

        // Using /cookies/delete endpoint to delete a cookie
        HttpGet deleteCookieRequest = new HttpGet(BASE_URL + "/cookies/delete?cookieToDelete");
        HttpResponse response = httpClient.execute(deleteCookieRequest);

        // Verify the response status code
        assertEquals(200, response.getStatusLine().getStatusCode());

        // Using /cookies endpoint to retrieve cookies
        HttpGet getCookiesRequest = new HttpGet(BASE_URL + "/cookies");
        response = httpClient.execute(getCookiesRequest);

        // Verify that the deleted cookie is not present in the response
        HttpEntity entity = response.getEntity();
        String cookies = EntityUtils.toString(entity);
        assertTrue(cookies.isEmpty() || !cookies.contains("cookieToDelete"));
    }
}
