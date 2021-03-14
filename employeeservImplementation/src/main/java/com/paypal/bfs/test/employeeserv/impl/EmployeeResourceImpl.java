package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.domain.EmployeeRecord;
import com.paypal.bfs.test.employeeserv.error.EmpNotExistsException;
import com.paypal.bfs.test.employeeserv.error.EmployeeServException;
import com.paypal.bfs.test.employeeserv.error.InvalidRequestException;
import com.paypal.bfs.test.employeeserv.model.EmpResponseDTO;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    DozerBeanMapper mapper;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        if(Integer.parseInt(id)<=0){
            throw new InvalidRequestException("Emp Id is incorrect");
        }

        //fetch employee details
        EmployeeRecord employeeRecord =employeeRepository.findByEmpId(Integer.parseInt(id));
        if(employeeRecord == null){
            throw new EmpNotExistsException("Employee doesn't exist");
        }

        //mapping entity object to dto
        mapper = new DozerBeanMapper();
        Employee employee=mapper.map(employeeRecord,Employee.class);
        if(employee != null){
        return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        else{
            // Custom Exception
            throw new EmployeeServException("Couldn't retrieve Employee details");
        }
    }

    @Override
    public ResponseEntity<EmpResponseDTO> createEmployee(Employee employee) {

        // validate request payload
        if(! isValidEmployee(employee)){
            throw new InvalidRequestException("Incorrect request payload");
        }

        // response object
        EmpResponseDTO empResponseDTO=new EmpResponseDTO();

        // validates if employee with same id already exists
        EmployeeRecord employeeRecord = employeeRepository.findByEmpId(employee.getId());
        if(employeeRecord != null){
            //Custom Exception
            empResponseDTO.setSuccess(false);
            empResponseDTO.setMessage("Employee already exists");
            return ResponseEntity.ok(empResponseDTO);
        }

        // maps Employee request object to Employee entity to persist in DB
        mapper = new DozerBeanMapper();
        employeeRecord =mapper.map(employee,EmployeeRecord.class);
        employeeRecord = employeeRepository.save(employeeRecord);


        if(employeeRecord !=null){
            empResponseDTO.setSuccess(true);
            empResponseDTO.setMessage("Employee succesfully created !");
            empResponseDTO.setData(mapper.map(employeeRecord,Employee.class));
            return ResponseEntity.ok(empResponseDTO);
        }

        else {
            //Custom Exception
            throw new EmployeeServException("Couldn't create Employee");
        }

    }

    /* validate employee request, basic validation checks can be done with
        Spring's built-in support for JSR 303 Bean Validation which makes
        it really easy. We just annotate our fields, with NotNull, Max, Min,
        Pattern annotations with desired parameters.
        Since the model is generated here by given json schema, I'm not making
        any change to this mechanism and keeping the POJO generation mechanism for
        Address class also same as used for Employee POJO

        In the absence of POJO in code, I'm doing the request validation as below and
        not relying on built-in Spring Boot Support of bean validation
     */
    private boolean isValidEmployee(Employee employee) {
        if (employee == null || employee.getId() == null
                || employee.getFirstName() == null || employee.getFirstName().isEmpty()
                || employee.getLastName() == null || employee.getLastName().isEmpty()
                || employee.getAddress() == null) {
            return false;
        }

        Address address = employee.getAddress();

        if (address.getLine1() == null || address.getLine1().isEmpty()
                || address.getCity() == null || address.getCity().isEmpty()
                || address.getCountry() == null || address.getCountry().isEmpty()
                || address.getState() == null || address.getState().isEmpty()
                || address.getZipCode() == null || address.getZipCode().isEmpty()) {
            return false;

        }

        return true;
    }

}
