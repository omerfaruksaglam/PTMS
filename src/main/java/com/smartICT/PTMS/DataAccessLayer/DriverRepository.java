package com.smartICT.PTMS.DataAccessLayer;

import com.smartICT.PTMS.Entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Override
    Optional<Driver> findById(Long ssn);
}
