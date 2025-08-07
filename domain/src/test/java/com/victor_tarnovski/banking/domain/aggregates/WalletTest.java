package com.victor_tarnovski.banking.domain.aggregates;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import com.victor_tarnovski.banking.domain.exceptions.InsufficientBalanceException;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.vo.Money;
import java.util.Currency;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WalletTest {
    static Stream<Arguments> currencyProvider() {
        return Stream.of(
            Arguments.of(Currency.getInstance("USD")),
            Arguments.of(Currency.getInstance("EUR")),
            Arguments.of(Currency.getInstance("BRL")),
            Arguments.of(Currency.getInstance("JPY"))
        );
    }
    
    Wallet givenWallet(long initialAmount, Currency currency) {
        UserId userId = new UserId(UUID.randomUUID());
        Wallet wallet = new Wallet(currency, userId);
        if (initialAmount > 0) {
            wallet.deposit(new Money(initialAmount, currency));
        }
        return wallet;
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenWallet_whenDeposit_thenBalanceIncreases(Currency currency) {
        // Given
        Wallet wallet = givenWallet(0, currency);
        Money depositAmount = new Money(1000, currency);

        // When
        wallet.deposit(depositAmount);

        // Then
        assertEquals(depositAmount, wallet.balance());
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenWalletWithBalance_whenWithdraw_thenBalanceDecreases(Currency currency) {
        // Given
        Wallet wallet = givenWallet(2000, currency);
        Money withdrawAmount = new Money(500, currency);

        // When
        wallet.withdraw(withdrawAmount);

        // Then
        assertEquals(new Money(1500, currency), wallet.balance());
    }

    @ParameterizedTest
    @MethodSource("currencyProvider")
    void givenWalletWithInsufficientBalance_whenWithdraw_thenThrowsException(Currency currency) {
        // Given
        Wallet wallet = givenWallet(100, currency);
        Money withdrawAmount = new Money(200, currency);

        // Then 
        assertThrows(
            InsufficientBalanceException.class, 
            () -> {
                // When
                wallet.withdraw(withdrawAmount);
            }
        );
    }
}
