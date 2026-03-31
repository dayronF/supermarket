package com.syncra.supermarket.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name = "categories")
public class CategorieEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name = "name")
private String name;

}