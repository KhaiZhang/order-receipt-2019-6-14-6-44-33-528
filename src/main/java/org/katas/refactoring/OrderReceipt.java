package org.katas.refactoring;

import javax.sound.sampled.Line;

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
        output.append(getCustomNameAndAddress());
        output.append(getLineItemAndSalesTax());
        return output.toString();
    }

    public String getPrintHeader() {
        return "======Printing Orders======\n";
    }

    public String getCustomNameAndAddress() {
        return o.getCustomerName()+o.getCustomerAddress();
    }

    public String getLineItemAndSalesTax() {
        StringBuilder output = new StringBuilder();
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : o.getLineItems()) {
            output.append(getLineItem(lineItem));
            totSalesTx += getSalesTax(lineItem);
            tot += getAmount(lineItem);
        }
        output.append(getStateTax(totSalesTx));
        output.append(getTotalAmount(tot));
        return output.toString();
    }

    public double getSalesTax(LineItem lineItem) {
        return lineItem.getTotalAmount() * .10;

    }

    public double getAmount(LineItem lineItem) {
        return lineItem.getTotalAmount() + getSalesTax(lineItem);
    }

    public String getStateTax(double totSalesTx) {
        return "Sales Tax"+'\t'+totSalesTx;
    }

    public String getTotalAmount(double tot) {
        return "Total Amount" + '\t' + tot;
    }

    public String getLineItem(LineItem lineItem) {
        return lineItem.getDescription() + '\t' + lineItem.getPrice() +
                '\t'+ lineItem.getQuantity() + '\t' + lineItem.getTotalAmount() + '\n';
    }
}