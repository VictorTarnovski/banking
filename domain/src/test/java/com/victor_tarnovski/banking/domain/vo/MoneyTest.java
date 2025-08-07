package com.victor_tarnovski.banking.domain.vo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.stream.Stream;

class MoneyTest {
    static Stream<Arguments> currencyProvider() {
        return Stream.of(
                Arguments.of(Currency.getInstance("USD")),
                Arguments.of(Currency.getInstance("EUR")),
                Arguments.of(Currency.getInstance("BRL")),
                Arguments.of(Currency.getInstance("JPY"))
            );
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenCurrency_whenConstructingMoney_thenAmountIsZeroAndCurrencyIsSet(Currency currency) {
        // When
        Money money = new Money(currency);

        // Then
        assertEquals(BigDecimal.valueOf(0L, currency.getDefaultFractionDigits()), money.amount());
        assertEquals(currency, money.currency());
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenLongAndCurrency_whenConstructingMoney_thenAmountAndCurrencyAreSet(Currency currency) {
        // Given
        long value = 1234L;

        // When
        Money money = new Money(value, currency);

        // Then
        assertEquals(BigDecimal.valueOf(value, currency.getDefaultFractionDigits()), money.amount());
        assertEquals(currency, money.currency());
    }

    @Test
    void givenDoubleAndCurrency_whenConstructingMoney_thenAmountAndCurrencyAreSet() {
        // Given
        double value = 12.34;
        var currency = Currency.getInstance("USD");

        // When
        Money money = new Money(value, currency);

        // Then
        assertEquals(BigDecimal.valueOf(1234, currency.getDefaultFractionDigits()), money.amount());
        assertEquals(currency, money.currency());
    }

    @Test
    void givenDoubleWithFractionDigitsAndCurrencyWithoutFractionDigits_whenConstructingMoney_thenThrowArithmeticException() {
        // Given
        double value = 12.34;
        var currency = Currency.getInstance("JPY");

        // Then
        assertThrows(
            ArithmeticException.class, 
            () -> {
                // When
                new Money(value, currency);
            }
        );
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenBigDecimalAndCurrency_whenConstructingMoney_thenAmountAndCurrencyAreSet(Currency currency) {
        // Given
        BigDecimal value = BigDecimal.valueOf(1000, currency.getDefaultFractionDigits());

        // When
        Money money = new Money(value, currency);

        // Then
        assertEquals(BigDecimal.valueOf(1000, currency.getDefaultFractionDigits()), money.amount());
        assertEquals(currency, money.currency());
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenAdd_thenSumIsCorrect(Currency currency) {
        // Given
        Money m1 = new Money(1000, currency);
        Money m2 = new Money(2500, currency);

        // When
        Money result = m1.add(m2);

        // Then
        assertEquals(new Money(3500, currency), result);
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenSubtract_thenDifferenceIsCorrect(Currency currency) {
        // Given
        Money m1 = new Money(3000, currency);
        Money m2 = new Money(1000, currency);

        // When
        Money result = m1.subtract(m2);

        // Then
        assertEquals(new Money(2000, currency), result);
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenMultiply_thenProductIsCorrect(Currency currency) {
        // Given
        Money m1 = new Money(20, currency);
        Money m2 = new Money(5, currency);

        // When
        Money result = m1.multiply(m2);

        // Then
        assertEquals(new Money(100, currency), result);
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenDivide_thenQuotientIsCorrect(Currency currency) {
        // Given
        Money m1 = new Money(100, currency);
        Money m2 = new Money(5, currency);

        // When
        Money result = m1.divide(m2);

        // Then
        assertEquals(new Money(20, currency), result);
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenMoney_whenAllocateByInt_thenAllocationIsCorrect(Currency currency) {
        // Given
        Money m = new Money(10, currency);

        // When
        Money[] allocated = m.allocate(3);

        // Then
        assertEquals(new Money(4, currency).amount(), allocated[0].amount());
        assertEquals(new Money(3, currency).amount(), allocated[1].amount());
        assertEquals(new Money(3, currency).amount(), allocated[2].amount());
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenMoney_whenAllocateByRatios_thenAllocationIsCorrect(Currency currency) {
        // Given
        Money m = new Money(10, currency);
        int[] ratios = { 2, 3, 5 };

        // When
        Money[] allocated = m.allocate(ratios);

        // Then
        assertEquals(new Money(2, currency).amount(), allocated[0].amount());
        assertEquals(new Money(3, currency).amount(), allocated[1].amount());
        assertEquals(new Money(5, currency).amount(), allocated[2].amount());
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenGreaterThan_thenReturnsTrue(Currency currency) {
        // Given
        Money m1 = new Money(2000, currency);
        Money m2 = new Money(1000, currency);

        // When & Then
        assertTrue(m1.greaterThan(m2));
        assertFalse(m2.greaterThan(m1));
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenGreaterThanOrEqual_thenReturnsTrue(Currency currency) {
        // Given
        Money m1 = new Money(2000, currency);
        Money m2 = new Money(2000, currency);
        Money m3 = new Money(1000, currency);

        // When & Then
        assertTrue(m1.greaterThanOrEqual(m2));
        assertTrue(m1.greaterThanOrEqual(m3));
        assertFalse(m3.greaterThanOrEqual(m1));
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenLessThan_thenReturnsTrue(Currency currency) {
        // Given
        Money m1 = new Money(1000, currency);
        Money m2 = new Money(2000, currency);

        // When & Then
        assertTrue(m1.lessThan(m2));
        assertFalse(m2.lessThan(m1));
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenTwoMoney_whenLessThanOrEqual_thenReturnsTrue(Currency currency) {
        // Given
        Money m1 = new Money(1000, currency);
        Money m2 = new Money(1000, currency);
        Money m3 = new Money(2000, currency);

        // When & Then
        assertTrue(m1.lessThanOrEqual(m2));
        assertTrue(m1.lessThanOrEqual(m3));
        assertFalse(m3.lessThanOrEqual(m1));
    }

    @Test
    void givenBigDecimal_whenToLong_thenReturnsCorrectLong() {
        // Given
        var currency = Currency.getInstance("USD");
        var money = new Money(currency);
        var value = new BigDecimal("123.45");

        // When
        var result = money.toLong(value);

        // Then
        assertEquals(12345L, result);
    }

    @Test
    void givenBigDecimalWithFractionDigitsAndCurrencyWithoutFractionDigits_whenToLong_thenThrowArithmeticException() {
        // Given
        var currency = Currency.getInstance("JPY");
        var money = new Money(currency);
        var value = new BigDecimal("123.45");

        // Then
        assertThrows(
            ArithmeticException.class, 
            () -> {
                // When
                money.toLong(value);
            }
        );
    }
}