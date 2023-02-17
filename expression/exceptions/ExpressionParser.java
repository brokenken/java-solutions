package expression.exceptions;

import expression.*;

import java.util.Scanner;

public class ExpressionParser implements TripleParser {
    private enum TypeParsing {
        HIGH_OPERATION(3),
        MIDDLE_OPERATION(2),
        LOW_OPERATION(1);
        private final int cost;
        TypeParsing (int cost) {
            this.cost = cost;
        }
    }

    @Override
    public TripleExpression parse(String expression) throws ParsingException {
        return new parseExpression(expression).parse(TypeParsing.LOW_OPERATION, false);
    }

    private static class parseExpression {
        private int pos;
        private final String source;
        private char ch;
        private final char END = '\0';

        public parseExpression(String source) {
            this.source = source;
            take();
        }

        private void take() {
            ch = pos < source.length() ? source.charAt(pos++) : END;
        }

        private String takeStr() {
            StringBuilder str = new StringBuilder();
            while (!Character.isWhitespace(ch) && !checkEndOfOperation(ch) && !checkEnd()) {
                str.append(ch);
                take();
            }
            return str.toString();
        }

        private boolean checkEnd() {
            return ch == END;
        }

        private void skipWhitespace() {
            while (!checkEnd()) {
                if (Character.isWhitespace(ch)) {
                    take();
                } else {
                    break;
                }
            }
        }

        private boolean checkEndOfOperation(char c) {
            return c == '-' || c == '(';
        }

        private PriorityExpression parse(TypeParsing typeParsing, boolean inBrackets) throws OperationParseException {
            skipWhitespace();
            PriorityExpression prev = parseUnaryOperation();
            while (!checkEnd()) {
                skipWhitespace();
                if (typeParsing.cost == TypeParsing.HIGH_OPERATION.cost) {
                    if (ch == '+' || ch == '-') {
                        return prev;
                    }
                }
                if (typeParsing.cost >= TypeParsing.MIDDLE_OPERATION.cost) {
                    if (ch == 'g' || ch == 'l') {
                        return prev;
                    }
                }
                if (ch == '+') {
                    take();
                    prev = new CheckedAdd(prev, parse(TypeParsing.HIGH_OPERATION, inBrackets));
                } else if (ch == '*') {
                    take();
                    prev = new CheckedMultiply(prev, parseUnaryOperation());
                } else if (ch == '-') {
                    take();
                    prev = new CheckedSubtract(prev, parse(TypeParsing.HIGH_OPERATION, inBrackets));
                } else if (ch == '/') {
                    take();
                    prev = new CheckedDivide(prev, parseUnaryOperation());
                } else if (ch == ')' && inBrackets) {
                    break;
                } else {
                    int prevPos = pos;
                    String str = takeStr();
                    switch (str) {
                        case "gcd" -> prev = new CheckedGcd(prev, parse(TypeParsing.MIDDLE_OPERATION, inBrackets));
                        case "lcm" -> prev = new CheckedLcm(prev, parse(TypeParsing.MIDDLE_OPERATION, inBrackets));
                        default -> throw new OperationParseException(prevPos + "..." + pos);
                    }
                }
                skipWhitespace();
            }
            return prev;
        }

        private PriorityExpression parseUnaryOperation() throws OperandParseException {
            skipWhitespace();
            if (Character.isDigit(ch) || ch == 'x' || ch == 'y' || ch == 'z') {
                return parseNumber(false);
            } else if (ch == '-') {
                return parseNumber(true);
            } else if (ch == '(') {
                return parseBrackets();
            }
            int prevPos = pos;
            String str = takeStr();
            switch (str) {
                case "reverse" -> {
                    return new CheckedReverse(parseNumber(false));
                }
                case "pow10" -> {
                    return new CheckedPow10(parseNumber(false));
                }
                case "log10" -> {
                    return new CheckedLog10(parseNumber(false));
                }
                default -> throw new OperandParseException(prevPos + "..." + pos);
            }
        }

        private PriorityExpression parseBrackets() throws BracketsParseException {
            take();
            PriorityExpression prev = parse(TypeParsing.LOW_OPERATION, true);
            skipWhitespace();
            if (ch != ')') {
                throw new BracketsParseException();
            } else {
                take();
                return prev;
            }
        }

        private PriorityExpression parseNumber(boolean isNegate) {
            StringBuilder num = new StringBuilder();
            skipWhitespace();
            if (ch == 'x' || ch == 'y' || ch == 'z') {
                char tmp = ch;
                take();
                return new Variable(Character.toString(tmp));
            }
            if (isNegate) {
                num.append(ch);
                take();
            }
            if (!Character.isDigit(ch)) {
                if (isNegate) {
                    return new CheckedNegate(parseUnaryOperation());
                } else {
                    return parseUnaryOperation();
                }
            }
            while (!checkEnd()) {
                if (Character.isDigit(ch)) {
                    num.append(ch);
                } else {
                    break;
                }
                take();
            }
            int number;
            try {
                number = Integer.parseInt(num.toString());
            } catch (NumberFormatException e) {
                throw new NumberParseException();
            }
            return new Const(number);
        }
    }
}
