/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoiceproject.InvModel;

/**
 *
 * @author Wael
 */
public class InvoiceLine {
    private String item;
    private double inv_prices;
    private int inv_count;
    private InvoiceHedear invoice; 

    public InvoiceLine(String item, double inv_prices, int inv_count, InvoiceHedear invoice) {
        this.item = item;
        this.inv_prices = inv_prices;
        this.inv_count = inv_count;
        this.invoice = invoice;
    }

    public InvoiceLine(int n,String itemrname,String price, String count) {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getInv_prices() {
        return inv_prices;
    }

    public void setInv_prices(double inv_prices) {
        this.inv_prices = inv_prices;
    }

    public int getInv_count() {
        return inv_count;
    }

    public void setInv_count(int inv_count) {
        this.inv_count = inv_count;
    }

    public InvoiceHedear getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHedear invoice) {
        this.invoice = invoice;
    }
    public double getTotal(){
        return getInv_count() * getInv_prices();
    }
}
