/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoiceproject.InvModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Wael
 */
public class LineModel extends AbstractTableModel {
private String[] colums ={"Item Name","Item Price","Count","Item Total"};
private List<InvoiceLine> line;

    public LineModel(List<InvoiceLine> line) {
        this.line = line;
    }

     public List<InvoiceLine> getLine() {
        return line;
    }

    @Override
    public int getRowCount() {
        return line.size();
    }

    @Override
    public int getColumnCount() {
        return colums.length;
    }

    @Override
    public String getColumnName(int i) {
        return colums[i];
    }
    

    @Override
    public Object getValueAt(int i, int i1) {
          
        InvoiceLine lines = line.get(i);
        switch (i1){
            case 0:
               return lines.getItem();
            case 1:
                return lines.getInv_prices();
            case 2:
                return lines.getInv_count();
            case 3:
                return lines.getTotal();
        }
        return 0;
    }
    
}
