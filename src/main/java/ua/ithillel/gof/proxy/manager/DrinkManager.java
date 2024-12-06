package ua.ithillel.gof.proxy.manager;

import ua.ithillel.gof.proxy.model.Drink;

public interface DrinkManager {
    Drink getDrinkByName(String name);
}
