package org.tbk.ngtor.util;

public final class ConsoleColors {
    private static final String ANSI_RESET = "\u001B[0m";

    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_ITALIC = "\u001B[3m";
    private static final String ANSI_UNDERLINE = "\u001B[4m";
    private static final String ANSI_REVERSED = "\u001B[7m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final String ANSI_BRIGHT_BLACK = "\u001B[30;1m";
    private static final String ANSI_BRIGHT_RED = "\u001B[31;1m";
    private static final String ANSI_BRIGHT_GREEN = "\u001B[32;1m";
    private static final String ANSI_BRIGHT_YELLOW = "\u001B[33;1m";
    private static final String ANSI_BRIGHT_BLUE = "\u001B[34;1m";
    private static final String ANSI_BRIGHT_PURPLE = "\u001B[35;1m";
    private static final String ANSI_BRIGHT_CYAN = "\u001B[36;1m";
    private static final String ANSI_BRIGHT_WHITE = "\u001B[37;1m";

    private static final String ANSI_BG_BLACK = "\u001B[40m";
    private static final String ANSI_BG_RED = "\u001B[41m";
    private static final String ANSI_BG_GREEN = "\u001B[42m";
    private static final String ANSI_BG_YELLOW = "\u001B[43m";
    private static final String ANSI_BG_BLUE = "\u001B[44m";
    private static final String ANSI_BG_PURPLE = "\u001B[45m";
    private static final String ANSI_BG_CYAN = "\u001B[46m";
    private static final String ANSI_BG_WHITE = "\u001B[47m";

    private static final String ANSI_BG_BRIGHT_BLACK = "\u001B[40;1m";
    private static final String ANSI_BG_BRIGHT_RED = "\u001B[41;1m";
    private static final String ANSI_BG_BRIGHT_GREEN = "\u001B[42;1m";
    private static final String ANSI_BG_BRIGHT_YELLOW = "\u001B[43;1m";
    private static final String ANSI_BG_BRIGHT_BLUE = "\u001B[44;1m";
    private static final String ANSI_BG_BRIGHT_PURPLE = "\u001B[45;1m";
    private static final String ANSI_BG_BRIGHT_CYAN = "\u001B[46;1m";
    private static final String ANSI_BG_BRIGHT_WHITE = "\u001B[47;1m";

    private ConsoleColors() {
        throw new UnsupportedOperationException();
    }

    public static String bold(String val) {
        return colorize(val, ANSI_BOLD);
    }

    public static String underline(String val) {
        return colorize(val, ANSI_UNDERLINE);
    }

    public static String italic(String val) {
        return colorize(val, ANSI_ITALIC);
    }

    public static String green(String val) {
        return colorize(val, ANSI_GREEN);
    }

    public static String red(String val) {
        return colorize(val, ANSI_RED);
    }

    public static String colorize(String val, String ansi) {
        return ansi + val + ANSI_RESET;
    }
}
