package com.victor_tarnovski.banking;

import java.util.Currency;

import com.victor_tarnovski.banking.domain.value_objects.Money;

public class Main {
    public static void main(String[] args) {
        var money = new Money(10, Currency.getInstance("BRL"));
        var alloc = new Money[]{};
        alloc = money.allocate(new int[]{3, 3, 3});
        print(alloc);
    }

    public static void print(Money[] arr) {
        System.out.println(arr.length);
        for (Money money : arr) {
            System.out.println(money.amount());   
        }
    }
}