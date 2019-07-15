package org.katas.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;



public class OrderReceiptTest {
    @Test
    public void shouldPrintCustomerInformationOnOrder() {
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.getCustomNameAndAddress();

        assertThat(output).contains("Mr X", "Chicago, 60601");
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.getLineItemAndSalesTax();

        assertThat(output).contains(
                "milk\t10.0\t2\t20.0\n",
                "biscuits\t5.0\t5\t25.0\n",
                "chocolate\t20.0\t1\t20.0\n",
                "Sales Tax\t6.5",
                "Total Amount\t71.5"
        );
    }

    @Test
    public void shouldPrinHeaderOnOrder() {
        //Given
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>());
        OrderReceipt receipt = new OrderReceipt(order);
        //When
        String headers =receipt.getPrintHeader();
        //Then
        assertThat(headers).contains("======Printing Orders======\n");
    }

    @Test
    public void shouldCalculateSalesTaxInformation() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, null));
        double output = receipt.getSalesTax(new LineItem("chocolate", 20.0, 1));
        Assertions.assertEquals(2.0,output);
    }

    @Test
    public void shouldCalculateTotalAmount() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, null));
        double output = receipt.getAmount(new LineItem("chocolate", 20.0, 1));
        Assertions.assertEquals(22.0,output);
    }
    @Test
    public void shouldPrintStateTax() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, null));
        String stateTax = receipt.getStateTax(22.0);
        assertThat(stateTax).contains("Sales Tax"+'\t'+22.0);
    }
    @Test
    public void shouldPrintTotalAmount() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, null));
        String tot = receipt.getTotalAmount(22.0);
        assertThat(tot).contains("Total Amount"+'\t'+22.0);
    }

    @Test
    public void shouldPrintLineItem() {
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, null));
        LineItem lineItem = new LineItem("chocolate", 20.0, 1);
        String result = receipt.getLineItem(lineItem);
        assertThat(result).contains("chocolate\t20.0\t1\t20.0\n");
    }

}
