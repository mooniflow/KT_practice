package com.hello.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CodeDetail {
    @Id
    private String codeId;
    private String groupId;
    private String codeName;
    private String codeValue;
    private int sortOrder;
    private boolean isActive;
}
