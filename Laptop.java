public class Laptop {
    private String manufacturer = "N/A";
    private String ram = "N/A";
    private String hdCap = "N/A";
    private String os = "N/A";
    private String color = "N/A";

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public void setRAM(String ram){
        this.ram = ram;
    }

    public void setHDCap(String hdCap){
        this.hdCap = hdCap;
    }

    public void setOS(String os){
        this.os = os;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public String getRAM(){
        return ram;
    }

    public String getHDCap(){
        return hdCap;
    }

    public String getOS(){
        return os;
    }

    public String getColor(){
        return color;
    }

    public boolean isEmpty(){
        return (manufacturer.equals("N/A") && ram.equals("N/A") && hdCap.equals("N/A") &&
                os.equals("N/A") && color.equals("N/A"));
    }

    @Override
    public String toString() {
        return String.format("Manuf: %10s   RAM: %2s GB   HDCap: %5s GB   OS: %10s   Color: %10s", manufacturer, ram, hdCap, os, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Laptop){
            Laptop laptop = (Laptop) obj;
            return manufacturer.equals(laptop.manufacturer) && ram.equals(laptop.ram) && hdCap.equals(laptop.hdCap) &&
            os.equals(laptop.os) && color.equals(laptop.color);
        }

        return false;
    }
    @Override
    public int hashCode() {
        return 3*manufacturer.hashCode() + 5*ram.hashCode() + 7*hdCap.hashCode() + 11*os.hashCode() + 13*color.hashCode();
    }
}