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
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');
            totSalesTx += getSalesTax(lineItem);

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + getSalesTax(lineItem);
        }

        // prints the state tax
        output.append("Sales Tax").append('\t').append(totSalesTx);

        // print total amount
        output.append("Total Amount").append('\t').append(tot);
        return output.toString();
    }

    public double getSalesTax(LineItem lineItem) {
        return lineItem.totalAmount() * .10;

    }
}