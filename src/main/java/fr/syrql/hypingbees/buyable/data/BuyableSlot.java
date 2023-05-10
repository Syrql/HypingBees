package fr.syrql.hypingbees.buyable.data;

import java.util.List;

public class BuyableSlot {

    /**
     * BuyableSlot Field slot
     *
     * @param slot the buyable slot.
     * @return The slot to buy
     */

    private int slot;

    /**
     * BuyableSlot Field price
     *
     * @param price the buyable slot price.
     * @return The price of the slot
     */

    private int price;
    /**
     * BuyableSlot Field placeholder
     *
     * @param price the placeholder slot.
     * @return The placeholder of the slot
     */

    private String placeholder;
    /**
     * BuyableSlot Field commands
     *
     * @param commands the buyable slot commands after purchasing.
     * @return The commands of the slot pruchase
     */

    private List<String> commands;

    /**
     * Constructor for class Boost
     *
     * @param slot
     * @param price
     * @param placeholder
     * @param commands
     */

    public BuyableSlot(int slot, int price, String placeholder, List<String> commands) {
        this.slot = slot;
        this.price = price;
        this.placeholder = placeholder;
        this.commands = commands;
    }

    /**
     * This is a getter which get the slot
     *
     * @return slot - the slot to be get
     */

    public int getSlot() {
        return slot;
    }

    /**
     * This is a setter which set the slot
     *
     * @param slot - the slot to be set
     */

    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * This is a getter which get the price
     *
     * @return price - the price to be get
     */

    public int getPrice() {
        return price;
    }

    /**
     * This is a setter which set the price
     *
     * @param price - the type to be set
     */

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * This is a getter which get the placeholder
     *
     * @return placeholder - the placeholder to be get
     */

    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * This is a setter which set the placeholder
     *
     * @param placeholder - the placeholder to be set
     */

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    /**
     * This is a getter which get the commands
     *
     * @return commands - the commands to be get
     */

    public List<String> getCommands() {
        return commands;
    }

    /**
     * This is a setter which set the commands
     *
     * @param commands - the commands to be set
     */

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
