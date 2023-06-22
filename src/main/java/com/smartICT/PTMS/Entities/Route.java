package com.smartICT.PTMS.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Route")
public class Route {
    @Id
    @Column(name = "plate")
    private String plate;

    @Column(name = "sid")
    private long sid;
}
