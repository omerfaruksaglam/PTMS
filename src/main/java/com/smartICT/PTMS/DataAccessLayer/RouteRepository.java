package com.smartICT.PTMS.DataAccessLayer;

import com.smartICT.PTMS.Entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,String> {
}
