package application.usecase;

public interface TransactionalManager {

	void begin();
	
	void commit();
	
	void rollback();
	
}
