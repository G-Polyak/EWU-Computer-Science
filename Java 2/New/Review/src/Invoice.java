import java.util.ArrayList;
import java.util.Date;

public class Invoice {

    private Integer orderNum;
    private Integer cusNum;
    private Date orderDate = new Date();
    private ArrayList<LineItem> lineItems;

    public Invoice(Integer orderNum, Integer cusNum) {
        this.orderNum = orderNum;
        this.cusNum = cusNum;
        this.lineItems = new ArrayList<LineItem>();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getCusNum() {
        return cusNum;
    }

    public void setCusNum(Integer cusNum) {
        this.cusNum = cusNum;
    }

    public void addItem(Item item, Integer q) {
        lineItems.add(new LineItem(item, q));
    }

    @Override
    public String toString() {
        String lines = lineItems.toString();
        lines = lines.substring(1, lines.length() - 1);
        lines = lines.replaceAll(", ", "\n");
        return "Order#\t" + "Customer#\t" + "Order Date\n" + orderNum +
                "\t\t" + cusNum + "\t\t" + orderDate + "\n\n" +
                "SKU\t\t" + "Description\t\t" + "Quantity\t" + "Extension\n" + lines;
    }

}