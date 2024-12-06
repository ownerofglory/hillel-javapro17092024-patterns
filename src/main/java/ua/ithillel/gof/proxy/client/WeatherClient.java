package ua.ithillel.gof.proxy.client;


import ua.ithillel.gof.proxy.client.response.OpenWeatherResponse;

public interface WeatherClient {
    OpenWeatherResponse getCurrentWeatherDataByQuery(String city) throws Exception;
}
