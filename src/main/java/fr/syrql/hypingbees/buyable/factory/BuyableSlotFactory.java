package fr.syrql.hypingbees.buyable.factory;

import fr.syrql.hypingbees.buyable.data.BuyableSlot;

import java.util.List;

public class BuyableSlotFactory {

    public BuyableSlot create(int slot, int price, String placeHolder, List<String> commands) {
        return new BuyableSlot(slot, price, placeHolder, commands);
    }
}
