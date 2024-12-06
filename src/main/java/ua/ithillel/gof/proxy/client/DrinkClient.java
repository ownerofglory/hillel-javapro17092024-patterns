package ua.ithillel.gof.proxy.client;

import ua.ithillel.gof.proxy.anno.Cacheable;
import ua.ithillel.gof.proxy.model.DrinkDbResponse;

public interface DrinkClient {
    @Cacheable
    DrinkDbResponse searchByName(String name);
}
