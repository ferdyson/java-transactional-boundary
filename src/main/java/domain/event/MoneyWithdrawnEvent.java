package domain.event;

import domain.AccountId;
import domain.Money;

public class MoneyWithdrawnEvent implements DomainEvent {

	private final AccountId sourceAccountId;
	private final AccountId targetAccountId;
	private final Money amount;
	
	public MoneyWithdrawnEvent(
			final AccountId sourceAccountId,
			final AccountId targetAccountId,
			final Money amount) {
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.amount = amount;
	}
	
	public AccountId getSourceAccountId() {
		return sourceAccountId;
	}
	
	public AccountId getTargetAccountId() {
		return targetAccountId;
	}
	
	public Money getAmount() {
		return amount;
	}
	
}
