package com.store.managerooms.CustomerTest;
import com.store.managerooms.controllers.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerControllerCanBeCreated {

    @Autowired
    CustomerController customerController;

    @Test
    public void ContextLoads() {
        assert customerController != null;
    }
}

