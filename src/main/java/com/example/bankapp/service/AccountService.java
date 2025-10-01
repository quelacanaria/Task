package com.example.bankapp.service;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class AccountService  implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;
    
	//check if the account is already in the system
	public Account findAccountByUsername(String username) {
		return accountRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Account not found"));
	}
	//check
	public Account registerAccount(String username, String password) {
		if(accountRepository.findByUsername(username).isPresent()) {
			throw new RuntimeException("Username Already Exist");
		}

		Account account = new Account();
		account.setUsername(username);
		account.setPassword(passwordEncoder.encode(password));
		account.setBalance(BigDecimal.ZERO);
        account.setRole("ROLE_USER");
		return accountRepository.save(account);
	}
	public void deposit(Account account, BigDecimal amount) {
		account.setBalance(account.getBalance().add(amount));
		accountRepository.save(account);

		Transaction transaction = new Transaction("Deposit",amount,LocalDateTime.now(),account );
		transactionRepository.save(transaction);
	}
	public void withdraw(Account account, BigDecimal amount) {
		if(account.getBalance().compareTo(amount)<0) {
			throw new RuntimeException("Insufficient funds");
		}
		account.setBalance(account.getBalance().subtract(amount));
		accountRepository.save(account);

		Transaction transaction = new Transaction("Withdrawal",amount,LocalDateTime.now(),account );
		transactionRepository.save(transaction);
	}
	public List<Transaction> getTransactionHistory(Account account){
		return transactionRepository.findByAccountId(account.getId());
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Account account = findAccountByUsername(username);
		if(account==null) {
			throw new UsernameNotFoundException("Username or password not found");
		}
	// Map the stored role String (e.g. "ROLE_USER" or "ROLE_ADMIN") to GrantedAuthority
	java.util.Collection<org.springframework.security.core.GrantedAuthority> authorities =
		java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority(account.getRole()));

	return new Account(
		account.getUsername(),
		account.getPassword(),
		account.getBalance(),
		account.getRole(),
		account.getTransaction(),
		authorities
	);
	}

	public void transferAmount(Account fromAccount, String toUsername, BigDecimal amount) {
		if (fromAccount.getBalance().compareTo(amount)<0) {
			throw new RuntimeException("Insufficient funds");
		}
		Account toAccount = accountRepository.findByUsername(toUsername)
				.orElseThrow(()->new RuntimeException("Recipient account not found"));

		fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
		accountRepository.save(fromAccount);

		toAccount.setBalance(toAccount.getBalance().add(amount));
		accountRepository.save(toAccount);

		Transaction debitTransaction = new Transaction("Transfer Out to "+toAccount.getUsername(),amount,LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),fromAccount);
		transactionRepository.save(debitTransaction);

		Transaction creditTransaction = new Transaction("Transfer In by "+fromAccount.getUsername(),amount,LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),toAccount);
		transactionRepository.save(creditTransaction);

	}
	 public List<Account> getAllAccounts() {
	        return accountRepository.findAll();
	    }

	public void deleteAccountByUsername(String username) {
		var opt = accountRepository.findByUsername(username);
		if (opt.isPresent()) {
			accountRepository.delete(opt.get());
		} else {
			throw new RuntimeException("Account not found");
		}
	}

	public void deleteAccountById(Long id) {
		// remove associated transactions first (if cascade isn't configured)
		// For simplicity assume FK cascade or TransactionRepository handles it; otherwise implement cleanup here.
		accountRepository.deleteById(id);
	}

	public Account findAccountById(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
	}

	/**
	 * Update an account's username and/or password while preserving role and balance.
	 * If newUsername is provided and different, it checks uniqueness.
	 * If newPassword is provided (non-blank), it is encoded and saved.
	 */
	public Account updateAccountDetails(Long id, String newUsername, String newPassword) {
		Account account = findAccountById(id);

		if (newUsername != null && !newUsername.isBlank() && !newUsername.equals(account.getUsername())) {
			var existing = accountRepository.findByUsername(newUsername);
			if (existing.isPresent()) {
				throw new RuntimeException("Username already exists");
			}
			account.setUsername(newUsername);
		}

		if (newPassword != null && !newPassword.isBlank()) {
			account.setPassword(passwordEncoder.encode(newPassword));
		}

		return accountRepository.save(account);
	}

}

