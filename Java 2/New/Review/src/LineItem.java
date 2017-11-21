public class LineItem implements Comparable<LineItem> {

    private Item item;
    private Integer quantityOrdered;

    public LineItem(Item item, Integer quantityOrdered) {
        this.item = item;
        this.quantityOrdered = quantityOrdered;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Double getExtensionPrice() {
        return this.quantityOrdered * this.item.getPrice();
    }

    public Double getExtensionWeight() {
        return this.quantityOrdered * this.item.getWeight();
    }

    @Override
    public String toString() {
        return item.getSku() + "\t" + item.getDesc() + "\t\t" + quantityOrdered
                + "\t\t\t" + this.getExtensionPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LineItem)) return false;

        LineItem lineItem = (LineItem) obj;

        if (!item.equals(lineItem.item)) return false;
        return quantityOrdered.equals(lineItem.quantityOrdered);
    }

    @Override
    public int compareTo(LineItem that) {
        return this.item.getSku() - that.item.getSku();
    }

}