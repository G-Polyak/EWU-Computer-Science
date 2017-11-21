public class Item {

    private Integer sku;
    private String desc;
    private Double price;
    private int qoh;
    private Double weight;

    public Item(Integer sku, String desc, Double price, int qoh, Double weight) {
        this.sku = sku;
        this.desc = desc;
        this.price = price;
        this.qoh = qoh;
        this.weight = weight;
    }

    public Integer getSku() {
        return sku;
    }

    public String getDesc() {
        return desc;
    }

    public Double getPrice() {
        return price;
    }

    public int getQoh() {
        return qoh;
    }

    public Double getWeight() {
        return weight;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQoh(int qoh) {
        this.qoh = qoh;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return sku + ": " + desc + "\n$" + price + "\nQuantity on hand: " + qoh + "\nWeight: " + weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;

        Item item = (Item) obj;

        return sku.equals(item.sku);
    }

}