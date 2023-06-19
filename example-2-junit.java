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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class HttpClientExampleTest {

    @Mock
    private CloseableHttpClient httpClient;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHttpClientExample() throws IOException {
        // Mock the HTTP responses
        String mockValue = "Mock Value";
        HttpResponse mockGetResponse = mock(HttpResponse.class);
        HttpEntity mockEntity = mock(HttpEntity.class);

        when(httpClient.execute(any(HttpGet.class))).thenReturn(mockGetResponse);
        when(mockGetResponse.getEntity()).thenReturn(mockEntity);
        when(mockEntity.getContent()).thenReturn(mockValue);

        // Create the instance of HttpClientExample to be tested
        HttpClientExample httpClientExample = new HttpClientExample();

        // Mock the ObjectMapper to return a fixed JSON string
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        String mockRequestBody = "{\"value\":\"" + mockValue + "\"}";
        when(objectMapper.writeValueAsString(any())).thenReturn(mockRequestBody);

        // Perform the test
        httpClientExample.main(new String[0]);

        // Verify the expected interactions
        verify(httpClient, times(2)).execute(any(HttpGet.class));
        verify(httpClient, times(2)).close();
        verify(httpClient).execute(any(HttpPost.class));
        verify(objectMapper).writeValueAsString(any());

        // Add assertions as needed
    }
}


```
