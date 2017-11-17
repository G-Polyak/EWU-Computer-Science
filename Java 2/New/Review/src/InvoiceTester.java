import java.util.*;
import java.io.*;

public class InvoiceTester {

    public static void main(String args[]) throws IOException {

        ArrayList<Item> items = readItems();
        printInvoice(createInvoice(items));

    }

    private static ArrayList<Item> readItems() throws IOException{

        Scanner reader = new Scanner(new File("Items.txt"));
        ArrayList<Item> items = new ArrayList<Item>();
        int sku;
        String desc;
        double price;
        int qoh;
        double weight;

        while(reader.hasNextLine()) {

            sku = reader.nextInt();
            reader.nextLine();
            desc = reader.nextLine();
            price = reader.nextDouble();
            reader.nextLine();
            qoh = reader.nextInt();
            reader.nextLine();
            weight = reader.nextDouble();
            reader.nextLine();
            items.add(new Item(sku, desc, price, qoh, weight));

        }

        return items;

    }

    private static Invoice createInvoice(ArrayList<Item> items) throws IOException {

        Scanner reader = new Scanner(new File("order.txt"));
        int cusNum = reader.nextInt();
        reader.nextLine();
        int orderNum = reader.nextInt();
        reader.nextLine();
        Invoice invoice = new Invoice(orderNum, cusNum);

        int sku, quantity;
        while(reader.hasNextLine()) {

            sku = reader.nextInt();
            reader.nextLine();
            quantity = reader.nextInt();
            reader.nextLine();

            for(int i = 0; i < items.size(); i++) {
                if(sku == items.get(i).getSku() && items.get(i).getQoh() > 0) {
                    invoice.addItem(items.get(i), quantity);
                }
            }

        }

        return invoice;

    }

    private static void printInvoice(Invoice invoice) {
        System.out.print(invoice);
    }

}