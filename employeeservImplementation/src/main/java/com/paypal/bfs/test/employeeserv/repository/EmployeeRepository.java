package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.domain.EmployeeRecord;
import org.springframework.data.repository.CrudRepository;



public interface EmployeeRepository extends CrudRepository<EmployeeRecord, Integer> {


    EmployeeRecord findByEmpId(Integer integer);
}
