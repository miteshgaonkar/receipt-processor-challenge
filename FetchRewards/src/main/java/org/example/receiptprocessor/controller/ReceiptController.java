package org.example.receiptprocessor.controller;

import org.example.receiptprocessor.model.Receipts;
import org.example.receiptprocessor.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process/")
    public ResponseEntity<?> processReeceipt(@RequestBody Receipts receipt){
        String id = receiptService.processReceipt(receipt);
        return ResponseEntity.ok().body("{\"id\": \""+id+"\"}");
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id){
        Integer points = receiptService.getPoints(id);
        if (points != null) {
            return ResponseEntity.ok().body("{ \"points\": " + points + " }");
        } else {
            return ResponseEntity.status(404).body("{ \"error\": \"Receipt not found\" }");
        }
    }
}
