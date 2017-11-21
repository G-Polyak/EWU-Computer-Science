public abstract class LongInteger {

    private long value;

    public LongInteger(long value) {
        this.value = value;
    }

    public void calcValue(String operation, LongInteger that) {

        long ans = 0;
        if (operation.equals("+")) {
            ans = this.getValue() + that.getValue();
        }
        if (operation.equals("-")) {
            ans = this.getValue() - that.getValue();
        }
        if (operation.equals("*")) {
            ans = this.getValue() * that.getValue();
        }
        if (operation.equals("/")) {
            ans = this.getValue() / that.getValue();
        }
        that.setValue(ans);

    }

    public static boolean isValid(String input, int base) {

        char temp;
        boolean isValid = true;
        int i = 0;

        while (i < input.length() && isValid) {

            temp = input.charAt(i);
            if (base == 2 && temp != '0' && temp != '1') {
                isValid = false;
            }
            if (base == 8 && temp != '0' && temp != '1' && temp != '2' && temp != '3' &&
                    temp != '4' && temp != '5' && temp != '6' && temp != '7') {
                isValid = false;
            }
            if (base == 10 && temp != '0' && temp != '1' && temp != '2' && temp != '3' &&
                    temp != '4' && temp != '5' && temp != '6' && temp != '7' && temp != '8'
                    && temp != '9') {
                isValid = false;
            }
            if (base == 16 && temp != '0' && temp != '1' && temp != '2' && temp != '3' &&
                    temp != '4' && temp != '5' && temp != '6' && temp != '7' && temp != '8'
                    && temp != '9' && temp != 'A' && temp != 'B' && temp != 'C' && temp != 'D'
                    && temp != 'E' && temp != 'F') {
                isValid = false;
            }
            i++;

        }

        if (!isValid) {
            Interface.println("\nInvalid input for base: " + base + " \nValue is: " + input);
        }
        return isValid;

    }

    @Override
    public abstract String toString();

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}