package ua.ithillel.gof.proxy.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.gof.proxy.anno.Cacheable;
import ua.ithillel.gof.proxy.model.DrinkDbResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CocktailDbClient implements DrinkClient {
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/search.php";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public CocktailDbClient(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    @Cacheable
    public DrinkDbResponse searchByName(String name) {
        try {
            String url = BASE_URL + "?s=" + name.replace(" ", "%20");
            final HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(url))
                    .build();

            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                final String body = response.body();

                return objectMapper.readValue(body, DrinkDbResponse.class);
            }


        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
