package service;

import dao.AccountDao;
import filter.AccountFilter;
import vo.Account;

public class AccountService {

	private final int PARAM_ERROR = -1;
	private final int ACCOUNT_EXIST = 1;
	private final int SUCCESS = 0;
	
	public int addAccount(Account account) {
		if (! new AccountFilter(account).vaild()) {
			return PARAM_ERROR;
		}
		
		AccountDao accountDao = new AccountDao();
		if (accountDao.findByUsername(account) != null) {
			return ACCOUNT_EXIST;
		}
		
		accountDao.doAdd(account);
		return SUCCESS;
	}
	
	public int updateEmail(Account account) {
		if (! new AccountFilter(account).vaildEmail()) {
			return PARAM_ERROR;
		}
		
		AccountDao accountDao = new AccountDao();
		accountDao.doUpdate(account);
		return SUCCESS;
	}
}
