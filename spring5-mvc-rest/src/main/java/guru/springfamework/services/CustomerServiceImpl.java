package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;


    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws ResourceNotFoundException {
        // Get customer from repository by id, also check for isPresent
        return customerMapper.customerToCustomerDTO(customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnData = customerMapper.customerToCustomerDTO(savedCustomer);
        returnData.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnData;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public void patchCustomer(Long id, CustomerDTO customerDTO) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        // Patch customer
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());

        saveAndReturnDTO(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
