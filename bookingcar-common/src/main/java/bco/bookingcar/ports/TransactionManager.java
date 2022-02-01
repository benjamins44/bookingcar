package bco.bookingcar.ports;

import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;

public interface TransactionManager {
    interface FunctionInTransaction<R> {
        R execute() throws BusinessException, TechnicalException;
    }

    <R> R executeInTransaction(final FunctionInTransaction<R> function) throws BusinessException, TechnicalException;
}
