package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void doUpdateSleep4(Long id, String newName, String newLastName) {
//        Customer customer = customerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Customer customer = customerRepository.findOneAndLock(1L);
        try {
            System.out.println("Sleeping thread: " + Thread.currentThread().getName() );
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (newName == null) {
            newName = customer.getFirstName();
        }
        if (newLastName == null) {
            newLastName = customer.getLastName();
        }
        customer.setFirstName(newName);
        customer.setLastName(newLastName);
        customerRepository.save(customer);
    }

    @Transactional
    public void doUpdate(Long id, String newName, String newLastName) {
//        Customer customer = customerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Customer customer = customerRepository.findOneAndLock(1L);
        if (newName == null) {
            newName = customer.getFirstName();
        }
        if (newLastName == null) {
            newLastName = customer.getLastName();
        }
        customer.setFirstName(newName);
        customer.setLastName(newLastName);
        customerRepository.save(customer);
    }
}
