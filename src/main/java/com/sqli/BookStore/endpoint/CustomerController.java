package com.sqli.BookStore.endpoint;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.BookStore.dao.CustomerRepository;
import com.sqli.BookStore.entities.Customer;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/add")
	public ResponseEntity<?> save(@RequestBody Customer customerDto){
		Customer customer = customerRepository.save(customerDto);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@GetMapping("/find/all")
	public Set<Customer> findAll(){
		return customerRepository.findAll().stream().collect(Collectors.toSet());
	}
	
	@GetMapping("/find/id/{id}")
	public Customer findById(@PathVariable Integer id) {
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("No customer found with the given id["+id+"]"));
	}
	
	@GetMapping("/find/name/{lastname}")
	public Set<Customer> findByLastname(@PathVariable String lastname){
		return customerRepository.findByLastname(lastname).stream().collect(Collectors.toSet());
	}
}
