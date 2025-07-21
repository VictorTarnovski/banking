package com.victor_tarnovski.banking.presentation;

public record Problem(
    String title,
    int status,
    String detail
) {
    public Problem(String title, int status) {
        this(title, status, null);
    }
}
