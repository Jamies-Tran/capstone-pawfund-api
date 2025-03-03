package com.paw.fund.app.modules.account.service;

import com.paw.fund.app.modules.account.domain.Account;
import com.paw.fund.app.modules.account.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account.domain.usecase.AccountId;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.NONE)
public class AccountServiceTest {

    @Mock
    private AccountQueryService query;

    @InjectMocks
    private AccountUseCaseService useCase;

    private Account mockAccount;

    @BeforeEach
    public void setup() {
         mockAccount = Account.builder()
                 .accountId(1L)
                 .identification("0123456789")
                 .firstName("Nguyen Van")
                 .lastName("B")
                 .email("nvb@gmail.com")
                 .phone("0981874736")
                 .address("43 Phan Van Tri, P2, Q5")
                 .dateOfBirth(LocalDate.of(1997, 2, 1))
                 .genderCode("MALE")
                 .genderName("Nam")
                 .statusCode("ACTIVE")
                 .statusName("HOAT DONG")
                 .build();
    }

    @Test
    void accountUseCaseService_GetAccount_ReturnAccount() {
        Long accountId = 1L;

        when(query.findById(accountId))
                .thenReturn(mockAccount);
        Account actual = useCase.getAccount(AccountId.of(accountId));

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(mockAccount, actual);

        verify(query, times(1)).findById(accountId);
    }


    @Test
    void accountUseCaseService_GetAccount_NotFound() {
        when(query.findById(any()))
                .thenThrow(new ResourceNotFoundException());

        ResourceNotFoundException actualExc = Assertions.assertThrows(ResourceNotFoundException.class, () -> useCase.getAccount(AccountId.of(any())));

        Assertions.assertEquals("Không tìm thấy tài nguyên.", actualExc.getMessage());
    }

    @Test
    void accountUseCaseService_GetAccountForAuth_ReturnAccount() {
        String email = "nvb@gmail.com";

        when(query.findByAccountEmail(any()))
                .thenReturn(mockAccount);

        Account actual = useCase.getAccountForAuth(AccountEmail.of(email));

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, mockAccount);

        verify(query, times(1)).findByAccountEmail(any());
    }

    @Test
    void accountUseCaseService_GetAccountForAuth_NotFound() {
        String email = "nvb@gmail.com";

        when(query.findByAccountEmail(any()))
                .thenThrow(new ResourceNotFoundException());

        ResourceNotFoundException actualExc = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> useCase.getAccountForAuth(AccountEmail.of(email)));

        Assertions.assertEquals("Không tìm thấy tài nguyên.", actualExc.getMessage());

        verify(query, times(1)).findByAccountEmail(any());
    }
}
