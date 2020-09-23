package com.cg.ewallet.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cg.ewallet.entity.Account;

public class AccountRepository {
	
	public static Map<Long,Account> accountRepository=new HashMap<Long,Account>();
	static {
		accountRepository.put((long) 252526,new Account(1500,"Yogesh Upadhyay",2563));
		accountRepository.put((long) 252529,new Account(2350,"Souvik Debroy",1205));
		accountRepository.put((long) 252523,new Account(5350,"Anumay Sah",1405));
		
	}
	

}
