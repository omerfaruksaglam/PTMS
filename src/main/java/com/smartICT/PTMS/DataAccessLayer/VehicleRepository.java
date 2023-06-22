package com.smartICT.PTMS.DataAccessLayer;

import com.smartICT.PTMS.Entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
}
