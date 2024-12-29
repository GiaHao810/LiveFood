package app.manager.client.service;

import app.manager.client.entity.Address;
import app.manager.client.exeption.resource.ResourceNotFoundException;
import app.manager.client.repository.SQLAddressRepository;
import app.manager.client.service.implement.AddressService;
import app.manager.client.service.implement.UserService;
import app.manager.client.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SQLAddressService implements AddressService {
    private final SQLAddressRepository repository;
    private final UserService userService;
    private final AuthenticationUtil authenticationUtil;

    @Override
    public void save(Address address) {
        repository.save(address);
    }

    @Override
    public List<Address> getAllAddress() {
        return repository.findAll();
    }

    @Override
    public Address findById(String id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Can not find Address's ID : " + id)
                );
    }

    @Override
    public void deleteAddress(String id) {
        repository.deleteById(id);
    }

    @Override
    public void addAddress(Address address) {
        String username = authenticationUtil.getCurrentUsername();
        address.setUser(
                userService.findByUsername(username)
        );
        save(address);
    }
}
