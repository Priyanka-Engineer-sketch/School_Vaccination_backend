package com.schoolvaccination.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long frontLinerId;

	private String completeName;

	@ManyToOne
	@JoinColumn(name = "front_liner_category_id")
	private FrontLinerCategory frontLinerCategory;

	private String contactNumber;
	private String emailAddress;
	private String facebookAccount;
}
