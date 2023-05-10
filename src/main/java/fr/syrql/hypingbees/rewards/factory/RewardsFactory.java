package fr.syrql.hypingbees.rewards.factory;

import fr.syrql.hypingbees.rewards.data.Rewards;

import java.util.ArrayList;

public class RewardsFactory {

    public Rewards create() {
        return new Rewards(new ArrayList<>(), new ArrayList<>());
    }
}
