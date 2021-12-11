package org.ggkezeris.tdd;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//classes = CachingConfig.class)
@AutoConfigureTestDatabase
public class CachingTest {

    @Autowired
    private CarService service;

    @MockBean
    private CarRepo carRepo;

    @Test
    public void caching(){
        given(carRepo.findByName(anyString())).willReturn(new Car("yaris","hybrid"));

        service.getCarDetails("yaris");
        service.getCarDetails("yaris");

        verify(carRepo,times(1)).findByName("yaris");
    }

}
