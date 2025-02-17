package com.hello.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "code_group_id", nullable = false)
    private CodeGroup codeGroup;

    @ManyToOne
    @JoinColumn(name = "code_id", nullable = false)
    private CodeDetail codeDetail;

    private String productName;
    private String productDescription;
    private int productPrice;
}
