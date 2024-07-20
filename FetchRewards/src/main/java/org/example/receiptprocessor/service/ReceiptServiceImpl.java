package org.example.receiptprocessor.service;

import org.example.receiptprocessor.model.Receipts;
import org.example.receiptprocessor.model.Items;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    Map<String,Integer> receiptData = new HashMap<>();

    @Override
    public String processReceipt(Receipts receipt){
        String id = UUID.randomUUID().toString();
        int points = calculatePoints(receipt);
        receiptData.put(id,points);
        return id;
    }

    @Override
    public int getPoints(String Id){
        return receiptData.get(Id);
    }

    private int calculatePoints(Receipts receipt) {
        int points = 0;

        // Rule 1: One point for every alphanumeric character in the retailer name
        points += receipt.getRetailor().chars().filter(Character::isLetterOrDigit).count();

        // Rule 2: 50 points if the total is a round dollar amount with no cents
        double total = Double.parseDouble(receipt.getTotal());
        if (total == Math.floor(total)) {
            points += 50;
        }

        // Rule 3: 25 points if the total is a multiple of 0.25
        if (total % 0.25 == 0) {
            points += 25;
        }

        // Rule 4: 5 points for every two items on the receipt
        points += (receipt.getItems().size() / 2) * 5;

        // Rule 5: If the trimmed length of the item description is a multiple of 3
        for (Items item : receipt.getItems()) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                double itemPrice = (item.getPrices());
                points += Math.ceil(itemPrice * 0.2);
            }
        }

        // Rule 6: 6 points if the day in the purchase date is odd
        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 == 1) {
            points += 6;
        }

        // Rule 7: 10 points if the time of purchase is after 2:00pm and before 4:00pm
        String[] timeParts = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);
        if (hour >= 14 && hour < 16) {
            points += 10;
        }

        return points;
    }

}
