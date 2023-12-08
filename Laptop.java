public class Laptop {
    private String model;
    private int ram;
    private int hdCap;
    private String os;
    private String color;

    @Override
    public String toString() {
        return String.format("%s: %d %d %s %s", model, ram, hdCap, os, color);
    }
}