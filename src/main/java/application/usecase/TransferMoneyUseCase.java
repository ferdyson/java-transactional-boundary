package application.usecase;

import application.event.EventBus;
import application.port.AccountRepository;
import domain.Account;
import domain.AccountId;
import domain.Money;

public class TransferMoneyUseCase {

	private final AccountRepository accountRepository;
	private final EventBus bus;
	private final TransactionalManager transactionalManager;
	
	public TransferMoneyUseCase(
			final AccountRepository accountRepository,
			final EventBus bus,
			final TransactionalManager transactionalManager) {
		this.accountRepository = accountRepository;
		this.bus = bus;
		this.transactionalManager = transactionalManager;
	}
	
	public void execute(
			final AccountId sourceId,
			final AccountId targetId,
			final Money amount) {
		try {
			transactionalManager.begin();
			Account source = accountRepository.findById(sourceId);
			source.withdrawn(amount, targetId);
			accountRepository.save(source);
			source.pullEvents().forEach((event) -> {
				bus.publish(event);
			});
			transactionalManager.commit();
		} catch(Exception e) {
			transactionalManager.rollback();
			throw e;
		}
	}
	
}
