import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import com.paypal.bfs.test.employeeserv.model.EmpResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;




@RunWith(SpringRunner.class)
@SpringBootTest(classes=EmployeeservApplication.class)
public class EmpServeTest{

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public EmployeeResource employeeService() {
            return new EmployeeResourceImpl();
        }
    }

    @Autowired
    private EmployeeResource employeeResource;


    public void addEmployee() {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Test");
        employee.setLastName("Test");
        employee.setDateOfBirth("1992-07-11");

        Address address=new Address();
        address.setCountry("India");
        address.setState("Karnataka");
        address.setCity("Bangalore");
        address.setLine1("ECity");
        address.setZipCode("560100");

        employee.setAddress(address);

       employeeResource.createEmployee(employee);

    }

    // validates get Employee operation
    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        addEmployee();
        ResponseEntity<Employee> found = employeeResource.employeeGetById("1");

        assertThat(found.getBody().getFirstName())
                .isEqualTo("Test");
    }


    // validates create Employee operation
    @Test
    public void whenValidId_thenCreateEmployee() {


        Employee employee = new Employee();
        employee.setId(2);
        employee.setFirstName("Rohit");
        employee.setLastName("Wadhwa");
        employee.setDateOfBirth("1992-07-11");

        Address address=new Address();
        address.setCountry("India");
        address.setState("Punjab");
        address.setCity("Abohar");
        address.setLine1("NWW");
        address.setZipCode("152116");

        employee.setAddress(address);

        ResponseEntity<EmpResponseDTO> created = employeeResource.createEmployee(employee);

        assertThat(created.getBody().isSuccess())
                .isEqualTo(true);
    }


}