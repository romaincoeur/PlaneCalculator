package fr.wildcodeschool.planecalculator;

/**
 * Created by romain on 03/01/17.
 */

public class PassengerModel {
    private String name;
    private int weight;

    public PassengerModel(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
