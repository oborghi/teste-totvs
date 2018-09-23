package br.com.crud.test.service;

import br.com.crud.test.dao.AddressDao;
import br.com.crud.test.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;

    public List<Address> getAllAddress() {
        return this.addressDao.findAll();
    }

    public Address addAddress(Address address) {
        return this.addressDao.save(address);
    }

    public Optional<Address> getAddressById(int id) {
        return this.addressDao.findById(id);
    }

    public Address updateAddress(Address address) {
        return this.addressDao.save(address);
    }

    public void deleteAddressById(int id) {
        this.addressDao.deleteById(id);
    }

    public void deleteAllAddress() {
        this.addressDao.deleteAll();
    }
}
