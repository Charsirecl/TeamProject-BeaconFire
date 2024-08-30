package com.az.authenticationservice.service;

import com.az.authenticationservice.domain.RegistrationToken;
import com.az.authenticationservice.domain.User;
import com.az.authenticationservice.exception.TokenNotFoundException;
import com.az.authenticationservice.repository.RegistrationTokenRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RegistrationTokenServiceTest {

    @Mock
    private RegistrationTokenRepo tokenRepository;
    @InjectMocks
    private RegistrationTokenService tokenService;

    @Test
    void testGetRegistrationToken() throws TokenNotFoundException {
        RegistrationToken registrationToken = new RegistrationToken(
                1L,
                "tired",
                "1@gmail.com",
                LocalDateTime.now(),
                new User()
        );

        Mockito.when(tokenRepository.getRegistrationTokenById(1)).thenReturn(registrationToken);
        RegistrationToken result = tokenService.getRegistrationTokenById(1);
        assertEquals(registrationToken,result);
    }

//    @Test
//    void testCreateRegistrationToken() throws TokenNotFoundException {
//        RegistrationToken registrationToken = new RegistrationToken(
//                1L,
//                "tired",
//                "1@gmail.com",
//                LocalDateTime.now(),
//                new User()
//        );
//
//        tokenService.save(registrationToken);
//        Mockito.verify(tokenRepository).save(registrationToken);
//    }

    @Test
    void testGetAllTokens() throws TokenNotFoundException {
        List<RegistrationToken> registrationTokens = Mockito.mock(List.class);
        Mockito.when(tokenRepository.getAllRegistrationTokens()).thenReturn(registrationTokens);

        List<RegistrationToken> result = tokenService.getAllRegistrationTokens();
        assertEquals(registrationTokens,result);
    }

}
