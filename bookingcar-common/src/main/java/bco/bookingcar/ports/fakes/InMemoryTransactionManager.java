package bco.bookingcar.ports.fakes;

import bco.bookingcar.annotation.Fake;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import bco.bookingcar.ports.TransactionManager;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicBoolean;

@Builder
@NoArgsConstructor
@Fake
public class InMemoryTransactionManager implements TransactionManager {
    private final AtomicBoolean useTransaction = new AtomicBoolean(false);

    public boolean hasUsedATransaction() {
        return useTransaction.get();
    }

    public void resetUsedTransaction() {
        useTransaction.set(false);
    }

    @Override
    public <R> R executeInTransaction(FunctionInTransaction<R> function) throws BusinessException, TechnicalException {
        useTransaction.set(true);
        return function.execute();
    }
}
