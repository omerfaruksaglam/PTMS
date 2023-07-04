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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Station")
public class Station {
    @Id
    @Column(name = "sid")
    private long sid;

    @Column(name = "sname")
    private String sname;

    @Column(name = "street")
    private String street;

}
