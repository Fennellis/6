public class Laptop {
    private String manufacturer = "N/A";
    private int ram;
    private int hdCap;
    private String os = "N/A";
    private String color = "N/A";

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public void setRAM(int ram){
        this.ram = ram;
    }

    public void setHDCap(int hdCap){
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

    public int getRAM(){
        return ram;
    }

    public int getHDCap(){
        return hdCap;
    }

    public String getOS(){
        return os;
    }

    public String getColor(){
        return color;
    }



    @Override
    public String toString() {
        return String.format("Manuf: %10s   RAM: %2d GB   HDCap: %5d GB   OS: %10s   Color: %10s", manufacturer, ram, hdCap, os, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Laptop){
            Laptop laptop = (Laptop) obj;
            return manufacturer.equals(laptop.manufacturer) && ram == laptop.ram && hdCap == laptop.hdCap &&
            os.equals(laptop.os) && color.equals(laptop.color);
        }

        return false;
    }
    @Override
    public int hashCode() {
        return manufacturer.hashCode() + 5*ram + 7*hdCap + 11*os.hashCode() + 13*color.hashCode();
    }
}