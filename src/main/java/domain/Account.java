package domain;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import domain.event.DomainEvent;
import domain.event.MoneyWithdrawnEvent;

public class Account {

	private final AccountId id;
	private Money balance;
	private boolean active;
	private final List<DomainEvent> events;
	
	public Account(
			final AccountId id,
			final Money balance) {
		this.id = id;
		this.balance = balance;
		this.active = true;
		this.events = new ArrayList<>();
	}
	
	public void withdrawn(
			final Money amount,
			final AccountId targetAccountId) {
		validateActive();
		validateBalance(amount);
		this.balance = balance.subtract(amount);
		this.events.add(new MoneyWithdrawnEvent(this.id, targetAccountId, amount));
	}

	public void deposit(
			final Money amount) {
		validateActive();
		this.balance = balance.add(amount);
	}
	
	private void validateActive() {
		if(!active) {
			String msg = "Account inactive";
			throw new IllegalStateException(msg);
		}
	}
	
	private void validateBalance(
			final Money amount) {
		if(balance.isLessThan(amount)) {
			String msg = "Insufficient funds";
			throw new IllegalStateException(msg);
		}
	}
	
	public AccountId getId() {
		return id;
	}
	
	public List<DomainEvent> pullEvents() {
		List<DomainEvent> copy = unmodifiableList(this.events);
		this.events.clear();
		return copy;
	}
	
}
