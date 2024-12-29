package app.manager.client.repository;

import app.manager.client.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SQLAddressRepository extends JpaRepository<Address, String> {
}
