package org.katas.refactoring;

import javax.sound.sampled.Line;
import java.util.List;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        output.append(getPrintHeader());
        output.append(o.getCustomerName()+o.getCustomerAddress());
        output.append(getLineItemAndSalesTax());
        output.append("Sales Tax"+'\t'+ getSalesTax(o.getLineItems()));
        output.append("Total Amount" + '\t' + getAmount(o.getLineItems()));
        return output.toString();
    }

    public String getPrintHeader() {
        return "======Printing Orders======\n";
    }


    public String getLineItemAndSalesTax() {
        StringBuilder output = new StringBuilder();
        for (LineItem lineItem : o.getLineItems()) {
            output.append(getLineItem(lineItem));
        }
        return output.toString();
    }

    public double getSalesTax(List<LineItem> lineItems) {
        double sum = lineItems.stream().mapToDouble(LineItem::getTotalAmount).sum();
        final double taxRate = 0.1d;
        return sum * taxRate;

    }

    public double getAmount(List<LineItem> lineItems) {
        double sum = lineItems.stream().mapToDouble(LineItem::getTotalAmount).sum();
        return sum + getSalesTax(lineItems);
    }


    public String getLineItem(LineItem lineItem) {
        return lineItem.getDescription() + '\t' + lineItem.getPrice() +
                '\t'+ lineItem.getQuantity() + '\t' + lineItem.getTotalAmount() + '\n';
    }
}