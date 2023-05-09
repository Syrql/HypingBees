package fr.syrql.hypingbees.buyable.data;

import java.util.List;

public class BuyableLine {

    private int slot;
    private int price;
    private String placeholder;
    private List<String> commands;

    public BuyableLine(int slot, int price, String placeholder, List<String> commands) {
        this.slot = slot;
        this.price = price;
        this.placeholder = placeholder;
        this.commands = commands;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
