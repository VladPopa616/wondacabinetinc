package com.wondacabinetinc.presentationlayer;

import com.wondacabinetinc.wondacabinetinc.businesslayer.OrderService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
public class OrderResourceTest {

    @Autowired
    OrderService orderService;
}
