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

    @Override
    public int hashCode() {
        return 3*model.hashCode() + 5*ram + 7*hdCap + 11*os.hashCode() + 13*color.hashCode();
    }
}