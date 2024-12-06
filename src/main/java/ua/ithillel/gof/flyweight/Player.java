package ua.ithillel.gof.flyweight;

public class Player {
    private String name;
    private ColorManager colorManager = new ColorManager();


    private Color color;

    public Player(String name, int red, int green, int blue) {
        this.name = name;
        this.color = colorManager.createColor(red, green, blue);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

}
