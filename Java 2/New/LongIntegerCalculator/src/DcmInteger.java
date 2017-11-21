public class DcmInteger extends LongInteger {

    public DcmInteger(String dcmValue) {
        super(Long.parseLong(dcmValue));
    }

    @Override
    public String toString() {

        Long temp = this.getValue();
        return temp.toString();

    }

}