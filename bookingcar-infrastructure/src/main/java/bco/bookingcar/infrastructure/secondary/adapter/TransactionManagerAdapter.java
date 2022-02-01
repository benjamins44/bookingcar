package bco.bookingcar.infrastructure.secondary.adapter;

import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import bco.bookingcar.ports.TransactionManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@AllArgsConstructor
@Component
public class TransactionManagerAdapter implements TransactionManager {
    private final PlatformTransactionManager transactionManager;

    @Override
    public <R> R executeInTransaction(FunctionInTransaction<R> function) throws TechnicalException, BusinessException {

        var transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionDefinition.setTimeout(10);
        var transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            var result = function.execute();
            commitTransaction(transactionStatus);
            return result;
        } catch (BusinessException exception) {
            rollbackTranscation(transactionStatus);
            throw exception;
        } catch (Exception exception) {
            rollbackTranscation(transactionStatus);
            throw new TechnicalException(exception);
        }
    }

    private void rollbackTranscation(TransactionStatus transactionStatus) {
        if (transactionStatus.isCompleted()) {
            return;
        }
        transactionManager.rollback(transactionStatus);
    }

    private void commitTransaction(TransactionStatus transactionStatus) {
        if (transactionStatus.isCompleted()) {
            return;
        }
        if (!transactionStatus.isRollbackOnly()) {
            transactionManager.commit(transactionStatus);
        } else {
            transactionManager.rollback(transactionStatus);
        }
    }

}
