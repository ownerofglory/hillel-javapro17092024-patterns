package ua.ithillel.gof.proxy.manager;

import ua.ithillel.gof.proxy.client.DrinkClient;
import ua.ithillel.gof.proxy.model.Drink;
import ua.ithillel.gof.proxy.model.DrinkDbResponse;

public class DefaultDrinkManager implements DrinkManager {
    private final DrinkClient drinkClient;

    public DefaultDrinkManager(DrinkClient drinkClient) {
        this.drinkClient = drinkClient;
    }

    @Override
    public Drink getDrinkByName(String name) {
        final DrinkDbResponse drinkDbResponse = drinkClient.searchByName(name);
        final Drink drink = drinkDbResponse.getDrinks().get(0);
        return drink;
    }
}
