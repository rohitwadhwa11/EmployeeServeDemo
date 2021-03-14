package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.domain.AddressRecord;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressRecord,Integer> {
}
