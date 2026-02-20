package application.handler;

import application.port.AccountRepository;
import application.usecase.TransactionalManager;
import domain.Account;
import domain.event.MoneyWithdrawnEvent;

public class EventWithdrawnHandler {

	private final AccountRepository accountRepository;
	private final TransactionalManager transactionalManager;
	
	public EventWithdrawnHandler(
			final AccountRepository accountRepository,
			final TransactionalManager transactionalManager) {
		this.accountRepository = accountRepository;
		this.transactionalManager = transactionalManager;
	}
	
	public void handle(
			final MoneyWithdrawnEvent event) {
		try {
			transactionalManager.begin();
			Account target = accountRepository.findById(event.getTargetAccountId());
			target.deposit(event.getAmount());
			accountRepository.save(target);
			transactionalManager.commit();
		} catch (Exception e) {
			transactionalManager.rollback();
			throw e;
		}
	}
	
}
