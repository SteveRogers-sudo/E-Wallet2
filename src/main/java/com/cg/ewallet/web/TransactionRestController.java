package com.cg.ewallet.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ewallet.dto.TransactionForm;
import com.cg.ewallet.exception.AccountException;
import com.cg.ewallet.exception.InsufficientException;
import com.cg.ewallet.service.TransactionService;

@RestController
public class TransactionRestController {
	
	@Autowired
	TransactionService txnService;
	
	@CrossOrigin
	@PostMapping("/dotxn")
	public String doTransaction(@RequestBody TransactionForm transactionForm) throws AccountException, InsufficientException {
		if(txnService.doTransaction(transactionForm.getFromUserId(), transactionForm.getToUserId(), transactionForm.getAmount())) {
			
			return "Transaction done";
		
		}
		else {
			return "Transaction Interrupted";
		}
	}
}
