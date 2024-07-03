package br.com.DiegoCasemiroFS.PicpayApi.repository;

import br.com.DiegoCasemiroFS.PicpayApi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
