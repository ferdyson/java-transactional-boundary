package domain;

import java.util.UUID;

public class AccountId {
	
	public static AccountId newId() {
		return new AccountId(UUID.randomUUID());
	}
	
	private final UUID id;
	
	private AccountId(
			final UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}
	
}
