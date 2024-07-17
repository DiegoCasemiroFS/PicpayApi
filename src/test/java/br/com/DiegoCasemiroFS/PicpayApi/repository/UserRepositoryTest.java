package br.com.DiegoCasemiroFS.PicpayApi.repository;

import br.com.DiegoCasemiroFS.PicpayApi.dtos.UserDTO;
import br.com.DiegoCasemiroFS.PicpayApi.entity.User;
import br.com.DiegoCasemiroFS.PicpayApi.entity.enums.UserType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("should get user successfully from database")
    void findUserByDocumentSuccess() {
        String document = "12312312312";
        UserDTO data = new UserDTO("Diego",
                "Casemiro",
                "12312312312",
                new BigDecimal(10),
                "teste@gmail.com",
                "1234",
                UserType.COMMON);
        this.createUser(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should not get user from database when suer not exists")
    void findUserByDocumentFailure() {
        String document = "12312312312";

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}