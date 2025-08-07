package com.victor_tarnovski.banking.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Currency;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.victor_tarnovski.banking.domain.aggregates.Wallet;
import com.victor_tarnovski.banking.domain.exceptions.RecursiveTransferException;
import com.victor_tarnovski.banking.domain.exceptions.UnauthorizedTransferException;
import com.victor_tarnovski.banking.domain.ids.UserId;
import com.victor_tarnovski.banking.domain.ids.WalletId;
import com.victor_tarnovski.banking.domain.ports.TransferAuthorizer;
import com.victor_tarnovski.banking.domain.vo.Money;

class TransferServiceTest {
    class AuthorizedTransferAuthorizer implements TransferAuthorizer {
        @Override
        public void authorize(Wallet fromWallet, Wallet toWallet, Money amount) {}
    }

    class UnauthorizedTransferAuthorizer implements TransferAuthorizer {
        @Override
        public void authorize(Wallet fromWallet, Wallet toWallet, Money amount) {
            throw new UnauthorizedTransferException();
        }
    }

    private static Wallet given_a_wallet(Currency currency, UserId userId) {
        return new Wallet(
            new WalletId(UUID.randomUUID()),
            currency,
            userId
        );
    }

    private static Stream<Arguments> transferAmountAndCurrencyProvider() {
        return Stream.of(
            Arguments.of(30, Currency.getInstance("USD")),
            Arguments.of(30, Currency.getInstance("EUR")),
            Arguments.of(30, Currency.getInstance("BRL")),
            Arguments.of(30, Currency.getInstance("JPY"))
        );
    }

    @ParameterizedTest
    @MethodSource("transferAmountAndCurrencyProvider")
    void givenSufficientBalance_whenTransferIsUnauthorized_thenUnauthorizedTransferExceptionIsThrown(
        long transferAmount,    
        Currency currency
    ) {
        // Given
        var fromUserId = new UserId(UUID.randomUUID());
        var fromWallet = given_a_wallet(currency, fromUserId);
        fromWallet.deposit(new Money(transferAmount, currency));

        var toUserId = new UserId(UUID.randomUUID());
        var toWallet = given_a_wallet(currency, toUserId);

        var authorizer = new UnauthorizedTransferAuthorizer();
        var sut = new TransferService(authorizer);

        // Then
        assertThrows(
            UnauthorizedTransferException.class, 
            () -> {
                // When
                sut.transfer(transferAmount, fromWallet, toWallet);
            }
        );
    }

    @ParameterizedTest
    @MethodSource("transferAmountAndCurrencyProvider")
    void givenSufficientBalance_whenTransferIsAuthorized_thenTransferIsSuccessful(
        long transferAmount, 
        Currency currency
    ) {
        // Given 
        var fromUserId = new UserId(UUID.randomUUID());
        var fromWallet = given_a_wallet(currency, fromUserId);
        fromWallet.deposit(new Money(transferAmount, currency));

        var toUserId = new UserId(UUID.randomUUID());
        var toWallet = given_a_wallet(currency, toUserId);

        var authorizer = new AuthorizedTransferAuthorizer();
        var sut = new TransferService(authorizer);

        // When  
        var tx = sut.transfer(transferAmount, fromWallet, toWallet);

        // Then 
        assertEquals(new Money(transferAmount, currency), tx.amount());
        assertEquals(tx.fromWalletId(), fromWallet.id());
        assertEquals(tx.toWalletId(), toWallet.id());
    }

    @ParameterizedTest
    @MethodSource("transferAmountAndCurrencyProvider")
    void givenSameWallet_whenTransferIsAttempted_thenRecursiveTransferExceptionIsThrown(
        long transferAmount,
        Currency currency
    ) {
        // Given 
        var userId = new UserId(UUID.randomUUID());
        var wallet = given_a_wallet(currency, userId);
        wallet.deposit(new Money(transferAmount, currency));

        var authorizer = new AuthorizedTransferAuthorizer();
        var sut = new TransferService(authorizer);

        // Then 
        assertThrows(
            RecursiveTransferException.class,
            () -> {
                // When
                sut.transfer(transferAmount, wallet, wallet);
            }
        );
    }

}
