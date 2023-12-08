public class Laptop {
    String model;
    int ram;
    int hdCap;
    String os;
    String color;

    @Override
    public String toString() {
        return String.format("%s: %d %d %s %s", model, ram, hdCap, os, color);
    }
}