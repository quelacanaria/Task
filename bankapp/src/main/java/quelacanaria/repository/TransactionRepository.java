package quelacanaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import quelacanaria.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByAccountId(Long accountId);

	

	
}
