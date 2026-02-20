package application.port;

import domain.Account;
import domain.AccountId;

public interface AccountRepository {

	Account findById(AccountId id);
	
	void save(Account account);
	
}
