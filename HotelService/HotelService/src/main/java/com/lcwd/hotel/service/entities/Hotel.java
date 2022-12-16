package com.lcwd.hotel.service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="hotel")
@Data
public class Hotel {
    @Id
    private String id;
    private String name;
    private String location;
    private String about;
}
