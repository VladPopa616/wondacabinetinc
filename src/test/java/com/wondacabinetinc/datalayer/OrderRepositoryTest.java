package com.wondacabinetinc.datalayer;

import com.wondacabinetinc.wondacabinetinc.WondacabinetincApplication;
import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@SpringBootConfiguration
@ContextConfiguration
public class OrderRepositoryTest {

    @MockBean
    private OrderRepository orderRepository;

    Date date = new Date();

    @BeforeEach
    public void setUpDB()
    {
        Order order1 = new Order(1,
                "Done",
                UUID.randomUUID().toString(),
                "Design",
                "Kitchen Cabinet",
                "White",
                "121 Address",
                "City",
                new Timestamp(date.getTime()),
                new Timestamp(date.getTime()),
                "Material",
                "Handle",
                "email@email.com");
        orderRepository.save(order1);

        Order order2 = new Order(2,
                "Done",
                UUID.randomUUID().toString(),
                "Design",
                "Bathroom Cabinet",
                "Black",
                "122 Address",
                "City",
                new Timestamp(date.getTime()),
                new Timestamp(date.getTime()),
                "Material",
                "Handle",
                "email2@email.com");
        orderRepository.save(order2);

        Order order3 = new Order(3,
                "Cancelled",
                UUID.randomUUID().toString(),
                "Design",
                "Bedroom Cabinet",
                "Green",
                "123 Address",
                "City",
                new Timestamp(date.getTime()),
                new Timestamp(date.getTime()),
                "Material",
                "Handle",
                "email3@email.com");
        orderRepository.save(order3);
    }

    @Test
    public void findByOrderStatusIsNotTest(){
        int orderList = orderRepository.findByOrderStatusIs("Cancelled").size();
        assertEquals(1, orderList);
    }
}
