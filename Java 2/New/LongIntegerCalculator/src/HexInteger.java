public class HexInteger extends LongInteger {

    public HexInteger(String hexValue) {
        super(Long.parseLong(hexValue, 16));
    }

    @Override
    public String toString() {
        return Long.toHexString(this.getValue()).toUpperCase() + " (Hex)";
    }

}