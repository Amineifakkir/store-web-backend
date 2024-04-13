package com.example.sales.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(
        origins = {
                "http://localhost:3000",
                "https://staging.example.com",
                "https://app.example.com"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequestMapping(path="api/v1/bill")
public class BillController {
    private final BillService billService;
    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
    @GetMapping
    public List<Bill> getBills(){
        return billService.getBills();
    }
    @PostMapping
    public List<Bill> addNewBill(@RequestBody BillDTO billDTO){
        billService.addNewBill(billDTO);
        return billService.getBills();
    }
    @DeleteMapping(path = "{billId}")
    public List<Bill> deleteBill(@PathVariable("billId") Long billId){
        billService.deleteBill(billId);
        return billService.getBills();
    }
    @PutMapping(path = "{billId}")
    public List<Bill> updateBill(
            @PathVariable("billId") Long billId,
            @RequestBody Bill bill){
        billService.updateBill(billId,bill);
        return billService.getBills();
    }
}
