/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoiceproject.InvModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import salesinvoiceproject.InvView.NewJFrame_View;

/**
 *
 * @author Wael
 */
public class HeaderModel extends AbstractTableModel {
private String[] colums = {"Invoice Number","Customer Name","Invoice Date","Invoice Total"};
private List<InvoiceHedear> invo;
    public HeaderModel(List<InvoiceHedear> invoi) {
       this.invo = invoi; 
    }
public List<InvoiceHedear> getinvoice() {
        return invo;
    }
    @Override
    public int getRowCount() {
        return invo.size();
    }

    @Override
    public int getColumnCount() {
        return colums.length;
    }
    @Override
    public String getColumnName(int ColumsIndex) {
        return colums[ColumsIndex];
    }

    @Override
    public Object getValueAt(int i, int i1) {
        InvoiceHedear in = invo.get(i);
        switch (i1){
            case 0 : 
                return in.getNumber();
            case 1 :
                return in.getCustomer_name();
            case 2 :
                return NewJFrame_View.da.format(in.getDate());
            case 3:
                return in.gettotal();
    }
        return "";
    }
    
    
    
}
