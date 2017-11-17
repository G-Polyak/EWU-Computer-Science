public class OctalInteger extends LongInteger {

    public OctalInteger(String octValue) {
        super(Long.parseLong(octValue, 8));
    }

    @Override
    public String toString() {
        return Long.toOctalString(this.getValue()) + " (Octal)";
    }

}