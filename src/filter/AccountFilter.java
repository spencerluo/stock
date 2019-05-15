package filter;

import java.util.regex.Pattern;

import vo.Account;
public class AccountFilter implements FilterInter{
	
	private Account account;
	
	public AccountFilter(Account account) {
		this.account = account;
	}
	
	public boolean vaild() {
		return vaildUsername() &&
				vaildPassword() &&
				vaildEmail();
	}
	
	public boolean vaildUsername() {
		String username = account.getUsername();
		if (username.isEmpty() || 
				username.length()< 6 ||
				username.length()> 20) {
			return false;
		}
		
		return true;
	}
	
	public boolean vaildPassword() {
		String password = account.getPassword();
		if (password.isEmpty() || 
				password.length()< 6 ||
				password.length()> 20) {
			return false;
		}
		
		return true;
	}
	
	public boolean vaildEmail() {
		String email = account.getEmail();
		String regex = ".+@.+\\.com";
		return Pattern.matches(regex, email);
		
	}
	
	public static void main(String[] args) {
		Account account = new Account();
		account.setUsername("sdsdfasd");
		account.setPassword("43asdfsd");
		account.setEmail("1@a.com");
		AccountFilter filter = new AccountFilter(account);
		System.out.println(filter.vaildEmail());
		System.out.println(filter.vaildUsername());
		System.out.println(filter.vaildPassword());
	}
	
	
	
	
}
