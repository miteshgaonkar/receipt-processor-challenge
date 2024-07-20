package org.example.receiptprocessor.service;

import org.example.receiptprocessor.model.Receipts;

public interface ReceiptService {
    String processReceipt(Receipts receipt);
    int getPoints(String id);
}
