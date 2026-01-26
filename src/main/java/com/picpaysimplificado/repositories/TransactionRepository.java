package com.picpaysimplificado.repositories;

import com.picpaysimplificado.core.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
