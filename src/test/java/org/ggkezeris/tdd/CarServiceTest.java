package org.ggkezeris.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class CarServiceTest {

  @Mock private CarRepo carRepo;

  @Autowired
  private CarService carService;

  @BeforeEach
  public void setUp() {
    carService = new CarService(carRepo);
  }

  @Test
  void getCarDetails_returnsCarInfo() {
    given(carRepo.findByName("yaris")).willReturn(new Car("yaris", "hybrid"));

    Car car = carService.getCarDetails("yaris");

    assertThat(car.getName()).isEqualTo("yaris");
    assertThat(car.getType()).isEqualTo("hybrid");
  }

  @Test
  public void getCarDetails_whenCarNotFound() {

    assertThatExceptionOfType(CarNotFoundException.class)
        .isThrownBy(
            () -> {
              given(carRepo.findByName("yaris")).willReturn(null);
              carService.getCarDetails("yaris");
            });
  }
}
