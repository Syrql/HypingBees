package fr.syrql.hypingbees.namedinventory.data;

public class NamedInventory {

    /**
     * NamedInventory type duration
     *
     * @param duration the current inventory duration type
     * @return duration of the beehive cycle
     */

    private int duration;

    /**
     * NamedInventory type inventoryName
     *
     * @param inventoryName the current inventory name
     * @return name of inventory
     */

    private String inventoryName;

    /**
     * Constructor of NamedInventory class
     *
     * @param duration Duration time for specified inventory
     * @param inventoryName Name of the specified inventory
     */

    public NamedInventory(int duration, String inventoryName) {
        this.duration = duration;
        this.inventoryName = inventoryName;
    }

    /**
     * This is a getter which get the duration
     *
     * @return duration - the duration to be get
     */

    public int getDuration() {
        return duration;
    }

    /**
     * This is a setter which set the duration
     *
     * @param duration - the duration to be set
     */

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * This is a getter which get the inventoryName
     *
     * @return inventoryName - the inventoryName to be get
     */

    public String getInventoryName() {
        return inventoryName;
    }

    /**
     * This is a setter which set the inventoryName
     *
     * @param inventoryName - the inventoryName to be set
     */

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }
}
