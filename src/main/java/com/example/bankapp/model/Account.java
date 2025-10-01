package com.example.bankapp.model;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
// ...existing code... (removed unused import)
import org.springframework.security.core.userdetails.UserDetails;
//import jakarta.transaction.Transaction;


@Entity
@Table(name="account")
public class Account implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	private BigDecimal balance;

    private String role;

	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;

    @Transient
	private Collection<? extends GrantedAuthority> authorities;

	public Account(String username, String password, BigDecimal balance, String role, List<Transaction> transaction,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
        this.role = role;
		this.transactions = transaction;
		this.authorities = authorities;
	}

	public Account() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role=role;
    }

    public List<Transaction> getTransaction() {
		return transactions;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transactions = transaction;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


//    public static void main(String[] args) {
//        Account user=new Account();
//                user.setUsername("gg");
//                user.getPassword();
//                user.getBalance();
//                user.getTransaction();
//        System.out.println(user.getUsername());
//    }
}

