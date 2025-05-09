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
@Table(name = "front_liner_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontLinerCategory {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@MongoId
	private String mongoId;

	private String categoryName;
	private String jobDescription;
}
