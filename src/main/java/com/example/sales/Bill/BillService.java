package com.example.sales.Bill;

import com.example.sales.client.Client;
import com.example.sales.client.ClientRepository;
import com.example.sales.product.Product;
import com.example.sales.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository billRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    @Autowired
    public BillService(BillRepository billRepository,
                       ClientRepository clientRepository,
                       ProductRepository productRepository
    ) {
        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    public void addNewBill(BillDTO billDTO) {
        Optional<Client> clientOptional = clientRepository .findById(billDTO.getClientId());
        Optional<Product> productOptional = productRepository.findById(billDTO.getProductId());
        if(clientOptional.isPresent() && productOptional.isPresent()){
            Bill bill = new Bill();
            bill.setClient(clientOptional.get());
            bill.setProduct(productOptional.get());
            bill.setQuantity(billDTO.getQuantity());
            billRepository.save(bill);
        }else {
            throw new IllegalStateException("client or product not found");
        }
    }

    public void deleteBill(Long billId) {
        boolean exists = billRepository.existsById(billId);
        if(!exists){
            throw  new IllegalStateException("Bill with Id "
            + billId
            + " does not exists");
        }
        billRepository.deleteById(billId);
    }
    @Transactional
    public void updateBill(Long billId, Bill bill) {
        Bill bill1 = billRepository.findById(billId)
                .orElseThrow(()-> new IllegalStateException(
                        "Bill with Id "
                        + billId
                        +" does not exists"
                ));
        if(bill.getQuantity() != null && !Objects.equals(bill1.getQuantity(),bill.getQuantity())){
            bill1.setQuantity(bill.getQuantity());
        }
    }
}
