package ma.enset.moustakimebankingback.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.moustakimebankingback.dtos.CustomerDTO;
import ma.enset.moustakimebankingback.entities.Customer;
import ma.enset.moustakimebankingback.exceptions.CustomerNotFoundException;
import ma.enset.moustakimebankingback.services.BankAccountservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestService {
    private BankAccountservice bankAccountservice;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountservice.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long custometID) throws CustomerNotFoundException {
        return bankAccountservice.getCustomer(custometID);
    }
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountservice.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountservice.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountservice.deleteCustomer(id);
    }
}
