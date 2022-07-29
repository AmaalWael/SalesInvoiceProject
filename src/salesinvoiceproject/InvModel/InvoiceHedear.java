/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoiceproject.InvModel;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Wael
 */
public class InvoiceHedear {
    private int number;
    private String Customer_name;
    private Date date;
    private ArrayList<InvoiceLine> lines;   

    public InvoiceHedear(int number, String Customer_name, Date date) {
        this.number = number;
        this.Customer_name = Customer_name;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String Customer_name) {
        this.Customer_name = Customer_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<InvoiceLine> getLines() {
        if(lines == null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    public double gettotal(){
       double totalprice =0.0;
       for (InvoiceLine line : getLines()){
           totalprice +=line.getTotal();
       }
       return totalprice;
    }
}
