package app.manager.client.service.implement;

import app.manager.client.entity.Address;

import java.util.List;

public interface AddressService {
    void save(Address address);
    List<Address> getAllAddress();
    Address findById(String id);
    void deleteAddress(String id);
    void addAddress(Address address);
}
