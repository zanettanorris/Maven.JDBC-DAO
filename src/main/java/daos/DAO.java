package daos;

import java.util.List;

public interface DAO {
    public CarDTO findById(int id);
    public List<CarDTO> findAllCar();
    public boolean update(CarDTO dto);
    public boolean create(CarDTO dto);
    public boolean delete(int id);
}