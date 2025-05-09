package com.schoolvaccination.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaccination_center")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCenter {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long centerId;

    @MongoId
    private String mongoId;

    private String centerName; 
    private String location;
    private Integer capacity;
}
