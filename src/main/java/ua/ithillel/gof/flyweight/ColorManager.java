package ua.ithillel.gof.flyweight;

import java.util.HashMap;
import java.util.Map;

// ColorPool
public class ColorManager {
    private static Map<String, Color> colors = new HashMap<>();

    public Color createColor(int red, int green, int blue) {
        String colorKey = String.format("%d-%d-%d", red, green, blue);

        Color color = colors.get(colorKey);
        if (color == null) {
            color = new Color(red, green, blue);
            colors.put(colorKey, color);
        }

        return color;
    }
}
