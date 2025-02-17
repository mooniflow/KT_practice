package com.hello.demo.entity;

public enum PetSize {
    SMALL(1),    // 소형견 (10kg 이하)
    MEDIUM(2),   // 중형견 (10kg~25kg)
    LARGE(3);    // 대형견 (25kg 초과)
    
    private final int value;
    
    PetSize(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static PetSize fromValue(int value) {
        for (PetSize size : PetSize.values()) {
            if (size.getValue() == value) {
                return size;
            }
        }
        throw new IllegalArgumentException("Invalid pet size value: " + value);
    }
}
