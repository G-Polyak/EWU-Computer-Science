public class BinaryInteger extends LongInteger {

    public BinaryInteger(String binValue) {
        super(Long.parseLong(binValue, 2));
    }

    @Override
    public String toString() {
        return Long.toBinaryString(this.getValue()) + " (Binary)";
    }

}