package bco.bookingcar.infrastructure.primary.error;

import java.util.Map;

final class ArgumentsReplacer {
    private static final String OPEN_MUSTACHE = "\\{\\{\\s*";
    private static final String CLOSE_MUSTACHE = "\\s*\\}\\}";

    private ArgumentsReplacer() {
    }

    public static String replaceArguments(String message, Map<String, String> arguments) {
        if (message == null || arguments == null) {
            return message;
        }

        String result = message;
        for (Map.Entry<String, String> argument : arguments.entrySet()) {
            String argumentRegex = OPEN_MUSTACHE + argument.getKey() + CLOSE_MUSTACHE;
            result = result.replaceAll(argumentRegex, argument.getValue());
        }

        return result;
    }
}
