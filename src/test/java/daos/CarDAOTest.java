package daos;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class CarDAOTest {

    CarDAO car = new CarDAO();
    CarDTO carDTO = new CarDTO(15, "Toyota", "Scion",1999,"Black",82917198);
    CarDTO carDTO1 = new CarDTO(16, "Honda", "Accord",2007,"Yellow",8917493);


    @Test
    public void extractCarFromResultSetTest() {
        CarDTO newCar = car.findById(8);
        Assert.assertEquals(newCar.getId(), newCar.getCarById());
    }

    @Test
    public void findByIdTest() {
        car.create(carDTO1);
        CarDTO newCar = car.findById(16);
        Assert.assertEquals("Honda", newCar.getMake());
        Assert.assertEquals("Accord", newCar.getModel());
        Assert.assertEquals(2007, newCar.getYear());
        Assert.assertEquals("Yellow", newCar.getColor());
        Assert.assertEquals(8917493, newCar.getVin());
        car.delete(16);
    }

    @Test
    public void findAllCarTest() {
        List<CarDTO> list = car.findAllCar();
        Integer expectedSize = 10;
        Integer actualSize = list.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void updateTest() {
        car.create(carDTO);
        CarDTO expected = new CarDTO(16, "Honda", "Accord",2007,"Yellow",8917493);
        car.update(expected);
        CarDTO actual = car.findById(16);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMake(), actual.getMake());
        assertEquals(expected.getModel(), actual.getModel());
        assertEquals(expected.getYear(), actual.getYear());
        assertEquals(expected.getColor(), actual.getColor());
        assertEquals(expected.getVin(), actual.getVin());
        car.delete(16);
    }

    @Test
    public void createTest() {
        assertTrue(car.create(carDTO));
        car.delete(15);
    }

    @Test
    public void deleteTest() {
        car.create(carDTO);
        assertEquals(9, carDTO.getId());
        car.delete(9);
        assertNull(car.findById(9));
    }
}