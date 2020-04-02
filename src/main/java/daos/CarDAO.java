package daos;

import java.sql.*;
import java.util.*;

public class CarDAO implements DAO{

    Connection connection = ConnectionFactory.getConnection();

    public CarDTO extractCarFromResultSet(ResultSet rs) throws SQLException
    {
        CarDTO carDTO = new CarDTO();
        carDTO.setId( rs.getInt("Id"));
        carDTO.setMake( rs.getString("Make"));
        carDTO.setModel( rs.getString("Model"));
        carDTO.setYear( rs.getInt("Year"));
        carDTO.setColor( rs.getString("Color"));
        carDTO.setVin( rs.getInt("VIN"));
        return carDTO;
    }


    public CarDTO findById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Car WHERE id=" + id);
            if (rs.next()) {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }


    public List<CarDTO> findAllCar() {

        List<CarDTO> cars = new ArrayList<CarDTO>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Car");
            while (rs.next()) {
                CarDTO carDTO = extractCarFromResultSet(rs);
                cars.add(carDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public boolean update(CarDTO dto) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Car SET make=?, model=?, year=?, color=?, vin=? WHERE id=" + dto.getId() + ";");
            ps.setString(1, dto.getMake());
            ps.setString(2, dto.getModel());
            ps.setInt(3, dto.getYear());
            ps.setString(4, dto.getColor());
            ps.setInt(5, dto.getVin());
            int i = ps.executeUpdate();
            if (i == 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean create(CarDTO dto) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO car VALUES (?,?,?,?,?,?);");
            ps.setInt(1, dto.getId());
            ps.setString(2, dto.getMake());
            ps.setString(3, dto.getModel());
            ps.setInt(4, dto.getYear());
            ps.setString(5, dto.getColor());
            ps.setInt(6, dto.getVin());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM Car WHERE id=" + id);
            if (i == 1) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}