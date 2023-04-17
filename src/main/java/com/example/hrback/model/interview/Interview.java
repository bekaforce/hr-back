package com.example.hrback.model.interview;

public enum Interview {
    TEST("TEST"), VIDEO("VIDEO"), TEXT("TEXT"), INTERVIEW("INTERVIEW");

    private final String type;
    Interview(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
