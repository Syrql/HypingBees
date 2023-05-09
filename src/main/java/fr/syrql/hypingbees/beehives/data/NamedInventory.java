package fr.syrql.hypingbees.beehives.data;

public class NamedInventory {


    private int duration;
    private String inventoryName;

    public NamedInventory(int duration, String inventoryName) {
        this.duration = duration;
        this.inventoryName = inventoryName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }
}
