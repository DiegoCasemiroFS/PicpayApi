package br.com.DiegoCasemiroFS.PicpayApi.repository;

import br.com.DiegoCasemiroFS.PicpayApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> finduserByDocument(String document);

    Optional<User> finduserById(Long id);
}
