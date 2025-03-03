package com.paw.fund.app.modules.account.repository;

import com.paw.fund.app.modules.account.repository.database.AccountEntity;
import com.paw.fund.app.modules.account.repository.database.IAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {
    @Mock
    private IAccountRepository repository;

    private AccountEntity mock;

    private Long mockAccountId;

    private String mockAccountEmail;

    @BeforeEach
    public void setup() {
        mock = AccountEntity.builder()
                .accountId(1L)
                .firstName("First Name")
                .lastName("Last Name")
                .email("email@gmail.com")
                .identification("012345678912")
                .phone("0981874736")
                .build();
        mockAccountId = 1L;
        mockAccountEmail = "email@gmail.com";
    }

    @Test
    void accountRepository_FindById_ReturnAccountEntity() {
        when(repository.findById(any())).thenReturn(Optional.of(mock));

        Optional<AccountEntity> actual = repository.findById(mockAccountId);

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(actual.get().getAccountId(), mockAccountId);
    }

    @Test
    void accountRepository_FindById_NotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        Optional<AccountEntity> actual = repository.findById(mockAccountId);

        Assertions.assertFalse(actual.isPresent());
    }

    @Test
    void accountRepository_FindByEmail_ReturnAccountEntity() {
        when(repository.findByEmail(any())).thenReturn(Optional.of(mock));

        Optional<AccountEntity> actual = repository.findByEmail(mockAccountEmail);

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(actual.get().getEmail(), mockAccountEmail);
    }
}
