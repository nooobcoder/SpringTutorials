package guru.springfamework.api.v1.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDTOTest {

    @Test
    public void testGettersAndSetters() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setCustomerUrl("/api/v1/customer/1");

        assertEquals("John", customerDTO.getFirstName());
        assertEquals("Doe", customerDTO.getLastName());
        assertEquals("/api/v1/customer/1", customerDTO.getCustomerUrl());
    }
}