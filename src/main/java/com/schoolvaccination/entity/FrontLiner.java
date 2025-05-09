package com.schoolvaccination.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "front_liner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontLiner {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long frontLinerId;

	@MongoId
	private String mongoId;

	private String completeName;

	@ManyToOne
	@JoinColumn(name = "front_liner_category_id")
	private FrontLinerCategory frontLinerCategory;

	private String contactNumber;
	private String emailAddress;
	private String facebookAccount;
}
