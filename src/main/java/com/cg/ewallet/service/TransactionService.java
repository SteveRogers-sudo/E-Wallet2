package com.cg.ewallet.service;

import com.cg.ewallet.exception.AccountException;
import com.cg.ewallet.exception.InsufficientException;

public interface TransactionService {
	
	public boolean doTransaction(String fromUserId,String toUserId,double amount) throws AccountException, InsufficientException;

}
