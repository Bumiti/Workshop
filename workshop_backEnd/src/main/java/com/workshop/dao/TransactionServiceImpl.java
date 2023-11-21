package com.workshop.dao;

import com.workshop.dto.TransactionDTO;
import com.workshop.model.Transaction;
import com.workshop.repositories.TransactionRepository;
import com.workshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private  final TransactionRepository transactionRepository;

    @Override
    public List<TransactionDTO> TRANSACTION_DTO_LIST() {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        List<Transaction> transactionList = transactionRepository.findAll();
            for(Transaction transaction : transactionList){
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO.setTransactionDate(transaction.getTransactionDate())
                        .setType(transaction.getType()).setAmount(transaction.getAmount())
                        .setStatus(transaction.getStatus()).setUser_Name(transaction.getUser().getUser_name());
                transactionDTOS.add(transactionDTO);
            }
        return transactionDTOS;
    }
}
