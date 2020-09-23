package com.cg.ewallet.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.ewallet.dao.EWalletDao;
import com.cg.ewallet.dao.EWalletJpaRepository;
import com.cg.ewallet.dao.EWalletTransactionJpaRepository;
import com.cg.ewallet.entity.Transaction;
import com.cg.ewallet.entity.User;
import com.cg.ewallet.exception.AccountException;
import com.cg.ewallet.exception.InsufficientException;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	EWalletDao eWalletDao;
	
	@Autowired
	EWalletTransactionJpaRepository transRepo;
	
	@Autowired
	EWalletJpaRepository userRepo;

	@Override
	public boolean doTransaction(String fromUserId, String toUserId, double amount) throws AccountException, InsufficientException {
		
		User fromUser=eWalletDao.viewUser(fromUserId);
		User toUser=eWalletDao.viewUser(toUserId);
		
		if(fromUser==null) throw new AccountException("Invalid Account");
		if(toUser==null) throw new AccountException("Invalid Account");
		
		if(fromUser.getUserBalance()<amount) throw new InsufficientException("Insufficient balance");
		
			fromUser.setUserBalance(fromUser.getUserBalance()-amount);
			toUser.setUserBalance(toUser.getUserBalance()+amount);
			
			Transaction fromTran=new Transaction();
			fromTran.setTransAmount(amount);
			fromTran.setTransType("debit");
			fromTran.setTransDate(LocalDate.now());
			fromTran.setUser(fromUser);
			
			Transaction toTran=new Transaction();
			toTran.setTransAmount(amount);
			toTran.setTransType("credit");
			toTran.setTransDate(LocalDate.now());
			toTran.setUser(toUser);
			
			//eWalletDao.addTransaction(fromTran);
			//eWalletDao.addTransaction(toTran);
			transRepo.save(fromTran);
			transRepo.save(toTran);
			eWalletDao.editUser(fromUser);
			eWalletDao.editUser(toUser);
//			userRepo.updateUser(fromUser.getUserBalance()-amount, fromUserId);
//			userRepo.updateUser(toUser.getUserBalance()+amount, toUserId);
			
			
		return true;
		
		
		
	}

}
