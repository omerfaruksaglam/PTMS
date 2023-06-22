package com.smartICT.PTMS.DataAccessLayer;

import com.smartICT.PTMS.Entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station,Long> {
}
