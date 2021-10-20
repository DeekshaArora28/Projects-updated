package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ClientOrder implements Serializable {

    private int clNumber;
    private String pizzaType;
    private int nbSlices;


    public ClientOrder(int clNumber, String pizzaType, int nbSlices) {
        this.clNumber = clNumber;
        this.pizzaType = pizzaType;
        this.nbSlices = nbSlices;
    }


    public int getClNumber() {
        return clNumber;
    }

    public void setClNumber(int clNumber) {
        this.clNumber = clNumber;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public int getNbSlices() {
        return nbSlices;
    }

    public void setNbSlices(int nbSlices) {
        this.nbSlices = nbSlices;
    }

    @NonNull
    @Override
    public String toString() {
        return "Client number: "+clNumber+ "Pzza Type:" +pizzaType+ "Nb. Slices:" +nbSlices+ "Amount:" +getAmount();
    }

    public float getAmount() {
        float amount = 0;
        if(pizzaType.equals("Vegetarian"))
            amount=nbSlices*2.5f;

        else
            if(pizzaType.equals("Cheese"))
                amount = nbSlices*2.0f;
                else
                    if(pizzaType.equals("Mexican"))
                        amount = nbSlices*2.4f;

        return amount;
    }


}
