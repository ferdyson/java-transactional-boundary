package domain;

import java.math.BigDecimal;

public final class Money {
	
	public static final Money of(
			final int value) {
		return new Money(BigDecimal.valueOf(value));
	}
	
	private final BigDecimal amount;

	private Money(
			final BigDecimal value) {
		ensureCannotNegative(value);
		this.amount = value;
	}

	private void ensureCannotNegative(
			final BigDecimal value) {
		if(value.signum() == -1) {
			String msg = "Money cannot be negative";
			throw new IllegalArgumentException(msg);
		}
	}

	public Money add(
			final Money other) {
		BigDecimal result = this.amount.add(other.amount);
		return new Money(result);
	}
	
	public Money subtract(
			final Money other) {
		BigDecimal result = this.amount.add(other.amount);
		return new Money(result);
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public boolean isLessThan(
			final Money other) {
		return this.amount.compareTo(other.amount) < 0;
	}
}
