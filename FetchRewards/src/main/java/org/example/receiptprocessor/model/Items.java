package org.example.receiptprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {

    private String shortDescription;
    private Double price;

    public String getShortDescription(){
        return shortDescription;
    }

    public void setShortDescription(String shortDescription){
        this.shortDescription = shortDescription;
    }

    public Double getPrices(){
        return price;
    }

    public void setPrices(Double price){
        this.price = price;
    }
}
