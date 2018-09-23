package br.com.crud.test.controller;

import br.com.crud.test.entity.Address;
import br.com.crud.test.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Address> getAllAddress() {
        return this.addressService.getAllAddress();
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAddress(@Valid @RequestBody Address address) {
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(this.addressService.addAddress(address), responseHeader, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAddress(@Valid @RequestBody Address address) {
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(this.addressService.updateAddress(address), responseHeader, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Address> getAddress(@PathVariable int id) {
        return this.addressService.getAddressById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public void deleteAllAddress() {
         this.addressService.deleteAllAddress();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAddress(@PathVariable int id) {
        this.addressService.deleteAddressById(id);
    }
}
