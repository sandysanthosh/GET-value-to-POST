```

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientExample {
    public static void main(String[] args) {
        // Make the GET request to retrieve the value
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://example.com/api/get");

        String value = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            value = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Create the UpdateCustomerRequest object with the value
        UpdateCustomerRequest request = new UpdateCustomerRequest(value);

        // Make the POST request with the UpdateCustomerRequest as the request body
        httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://example.com/api/post");

        try {
            // Convert the UpdateCustomerRequest to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(request);

            StringEntity requestEntity = new StringEntity(requestBody);
            httpPost.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(httpPost);
            // Process the response as needed
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Custom POJO for the UpdateCustomerRequest
    static class UpdateCustomerRequest {
        private String value;

        public UpdateCustomerRequest(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}


```
