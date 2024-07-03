package br.com.DiegoCasemiroFS.PicpayApi.service;

import br.com.DiegoCasemiroFS.PicpayApi.entity.User;
import br.com.DiegoCasemiroFS.PicpayApi.entity.enums.UserType;
import br.com.DiegoCasemiroFS.PicpayApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo Lojista não está autorizado a fazer esse tipo de transação");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public void findUserById(Long id) throws Exception{
        this.userRepository.finduserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveuser(User user){
        this.userRepository.save(user);
    }

}
