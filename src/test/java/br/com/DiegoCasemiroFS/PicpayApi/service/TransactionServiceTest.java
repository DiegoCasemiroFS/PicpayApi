package br.com.DiegoCasemiroFS.PicpayApi.service;

import br.com.DiegoCasemiroFS.PicpayApi.dtos.TransactionDTO;
import br.com.DiegoCasemiroFS.PicpayApi.entity.User;
import br.com.DiegoCasemiroFS.PicpayApi.entity.enums.UserType;
import br.com.DiegoCasemiroFS.PicpayApi.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("should create transaction successfully when everything is ok")
    void createTransactionSuccess() throws Exception{
        User sender = new User(1L,
                "Diego",
                "Casemiro",
                "12312312312",
                "diego@gmail.com",
                "1234",
                new BigDecimal(10),
                UserType.COMMON);
        User receiver = new User(2L,
                "Luisa",
                "Casemiro",
                "12312312313",
                "luisa@gmail.com",
                "5678",
                new BigDecimal(10),
                UserType.COMMON);

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authorizationService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        verify(repository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(20));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "Transacao realizada com sucesso");
        verify(notificationService, times(1)).sendNotification(receiver, "Transacao recebida com sucesso");
    }

/*    @Test
    @DisplayName("should throws excpetion when transaction is not allowed")
    void createTransactionFailure() {
    }*/
}