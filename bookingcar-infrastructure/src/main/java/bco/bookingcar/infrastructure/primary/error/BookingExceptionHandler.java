package bco.bookingcar.infrastructure.primary.error;

import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import bco.bookingcar.infrastructure.primary.error.status.HttpStatusErrorGetter;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class BookingExceptionHandler {
    private static String DEFAULT_KEY = "error.default";

    private final MessageSource messages;
    private final HttpStatusErrorGetter httpStatusErrorGetter;

    private final String sendReportUri = "https://dummypage.sendreport.com?";
    private final String currentApiVersion = "1.0";
    private final String domainName = "BookingCar";

    public BookingExceptionHandler(MessageSource messages, HttpStatusErrorGetter httpStatusErrorGetter) {
        Locale.setDefault(Locale.FRANCE);
        this.messages = messages;
        this.httpStatusErrorGetter = httpStatusErrorGetter;
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleBusinessException(BusinessException exception) {
        HttpStatus status = httpStatusErrorGetter.getStatut(exception);

        AppError error = new AppError(
                currentApiVersion,
                String.valueOf(status.value()),
                getMessage(exception.getMessage(), exception.getArguments()),
                domainName,
                exception.getReason(),
                exception.getMessage(),
                sendReportUri
        );

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleBusinessException(TechnicalException exception) {
        HttpStatus status = httpStatusErrorGetter.getStatut(exception);

        AppError error = new AppError(
                currentApiVersion,
                status.toString(),
                getMessage(exception.getMessage(), null),
                domainName,
                exception.getReason(),
                exception.getMessage(),
                sendReportUri
        );

        return new ResponseEntity<>(error, status);
    }

    private String getMessage(String messageKey, Map<String, String> arguments) {
        String text = getMessageFromSource(messageKey);

        return ArgumentsReplacer.replaceArguments(text, arguments);
    }

    private String getMessageFromSource(String messageKey) {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            return messages.getMessage(messageKey, null, locale);
        } catch (NoSuchMessageException e) {
            return messages.getMessage(DEFAULT_KEY, null, locale);
        }
    }
}
