package com.workshop.repositories;

import com.workshop.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.status = 'COMPLETED'")
    long countSuccessfulTransactions();
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.status = 'FAILED'")
    long countFailedTransactions();

    @Query("SELECT t FROM Transaction t WHERE t.status = 'COMPLETED' AND t.type = 'BUY_COURSE'")
    List<Transaction> findCompletedBuyCourseTransactions();


}
