//George Polyak
//CSCD 211-02
//No Extra Credit Attempted

public class IntDriver {

    public static void main(String[] args) {

        LongInteger num1 = null;
        LongInteger num2 = null;
        int base = 10;
        String operation = "";
        String option = displayMenu(num1, num2, operation, base);

        while (!option.equals("Q")) {

            if (option.equals("DCM")) {
                base = 10;
            } else if (option.equals("BIN")) {
                base = 2;
            } else if (option.equals("OCT")) {
                base = 8;
            } else if (option.equals("HEX")) {
                base = 16;
            } else if (option.equals("+") || option.equals("-") || option.equals("*") ||
                    option.equals("/")) {
                operation = option;
            } else if (option.equals("=")) {

                if (num1 != null && num2 != null) {

                    if (!operation.equals("/") || num2.getValue() != 0) {

                        num1.calcValue(operation, num2);
                        num1 = num2;
                        num2 = null;
                        operation = "";

                    } else {
                        Interface.println("\n\t\tInvalid Operation");
                        num2 = null;
                        operation = "";
                    }

                }

            } else if (LongInteger.isValid(option, base)) {

                if (operation.equals("") || num1 == null) {

                    if (base == 2) {
                        num1 = new BinaryInteger(option);
                    } else if (base == 8) {
                        num1 = new OctalInteger(option);
                    } else if (base == 10) {
                        num1 = new DcmInteger(option);
                    } else if (base == 16) {
                        num1 = new HexInteger(option);
                    }

                } else if (base == 2) {
                    num2 = new BinaryInteger(option);
                } else if (base == 8) {
                    num2 = new OctalInteger(option);
                } else if (base == 10) {
                    num2 = new DcmInteger(option);
                } else if (base == 16) {
                    num2 = new HexInteger(option);
                }

            }

            option = displayMenu(num1, num2, operation, base);

        }

    }

    private static String displayMenu(LongInteger num1, LongInteger num2, String operation, int base) {

        String option = "";
        while (option.equals("")) {

            if (base == 10) {
                Interface.println("\n\tDecimal Mode");
            } else if (base == 2) {
                Interface.println("\n\tBinary Mode");
            } else if (base == 8) {
                Interface.println("\n\tOctal Mode");
            } else if (base == 16) {
                Interface.println("\n\tHex Mode");
            }

            if (num1 == null) {
                Interface.print("\t");
            } else {
                Interface.print("\t" + num1);
            }
            Interface.print("\t" + operation);

            if (num2 == null) {
                Interface.println("\t");
            } else {
                Interface.println("\t" + num2);
            }

            Interface.println("\n\tBin - Binary\t\t\t+");
            Interface.println("\tOct - Octal\t\t\t\t-");
            Interface.println("\tDcm - Decimal\t\t\t*");
            Interface.println("\tHex - Hexadecimal\t\t/");
            Interface.println("\tQ   - Quit\t\t\t\t=");
            Interface.print("\tOption or value:  ");
            option = Interface.readString().toUpperCase();

        }
        return option;

    }

}