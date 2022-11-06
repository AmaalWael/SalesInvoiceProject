/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoiceproject.InvControl;

import java.awt.Dialog;
import java.awt.Dialog;
import java.awt.Dialog;
import java.awt.Dialog;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import salesinvoiceproject.InvModel.HeaderModel;
import salesinvoiceproject.InvModel.InvoiceHedear;
import salesinvoiceproject.InvModel.InvoiceLine;
import salesinvoiceproject.InvModel.LineModel;
import salesinvoiceproject.InvView.NewHeader;
import salesinvoiceproject.InvView.NewJFrame_View;
import salesinvoiceproject.InvView.NewLines;

/**
 *
 * @author Wael
 */
public class Invoice_Listener implements ActionListener , ListSelectionListener{
    private NewJFrame_View fram;
    private NewHeader newheader;
    private NewLines newlines;
     private InvoiceHedear invoice; 
    public Invoice_Listener (NewJFrame_View fram){
       this.fram = fram; 
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       System.out.println("action"); 
       String actioncommand = ae.getActionCommand();
       switch(actioncommand){
           case "Create_Invoice":
               createinvoice();
               break;
           case "Delete_Invoice":
               deleteinvoice();
               break;  
           case "Create_Line":
               createline();
               break;
           case"Delete_Line":
               deleteline();
               break;
           case "Load":
               load(null,null);
               break;
           case "Save":
       {
           try {
               save();
           } catch (IOException ex) {
               Logger.getLogger(Invoice_Listener.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
                      
               break;
           case "Submit The Invoice":
               SubmitTheInvoice();
                   break;
           case "Cancel The Invoice":
               CancelTheInvoice();
               break;
           case "Submit The NewLine":
               SubmitTheNewLine();
               break;
           case "Cancel The NewLine":
               CancelTheNewLine();
               break;
            
       }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        System.out.println("list");
        int r = fram.gettable_invoice().getSelectedRow();
        System.out.println("The row"+r);
        if(r> -1 && r < fram.getInvoic().size()){
        InvoiceHedear invoi = fram.getInvoic().get(r);
        //num name date 
        fram.getjLabel5().setText(""+invoi.getNumber());
        fram.getjLabel6().setText(invoi.getCustomer_name());
        fram.getjLabel7().setText( fram.da.format(invoi.getDate()));
        fram.getjLabel8().setText(""+invoi.gettotal());
      
        
        List <InvoiceLine> lines = invoi.getLines();
        //fram.gettable_line().setModel(new LineModel(lines));
        //fram.gettable_line().setModel(new LineModel(lines) );
        fram.gettable_line().setModel( new LineModel(lines));
    }
        else{
        fram.getjLabel5().setText("");
        fram.getjLabel6().setText( "");
        fram.getjLabel7().setText("");
        fram.getjLabel8().setText("");
        fram.gettable_line().setModel(new LineModel(new ArrayList<InvoiceLine>()));
        }
    }

    private void createinvoice() {
        newheader = new NewHeader(fram);
        newheader.setVisible(true);
        
    }

    private void deleteinvoice() {
       int r = fram.gettable_invoice().getSelectedRow();
       if(r != -1){
       fram.getInvoic().remove(r);
       ((HeaderModel)fram.gettable_invoice().getModel()).fireTableDataChanged();
       }
    }

    private void createline() {
        newlines = new NewLines(fram);
        newlines.setVisible(true);
    }

    private void deleteline() {
        int r = fram.gettable_line().getSelectedRow();
        if (r != -1){
            int hrow = fram.gettable_invoice().getSelectedRow();
            LineModel linemodel = (LineModel) fram.gettable_line().getModel();
            linemodel.getLine().remove(r);
            linemodel.fireTableDataChanged();
            ((HeaderModel)fram.gettable_invoice().getModel()).fireTableDataChanged();
            fram.gettable_invoice().setRowSelectionInterval(hrow, hrow);

        }
        
    }

    public void load(String hpath , String lpath) {
      File hfile = null;
      File lfile =null;
        if(hpath == null && lpath == null){ 
         JFileChooser jf = new JFileChooser();
         int r = jf.showOpenDialog(fram);
          if(r == JFileChooser.APPROVE_OPTION){
             hfile = jf.getSelectedFile();
             r = jf.showOpenDialog(fram);
             if (r == JFileChooser.APPROVE_OPTION){
                 lfile = jf.getSelectedFile();
             }
          }  
      }else{
            hfile = new File(hpath);
            lfile = new File(lpath);
        }
        if ( hfile != null && lfile != null){
            try{
               List<String> header_lines = Files.lines(Paths.get(hfile.getAbsolutePath())).collect(Collectors.toList());
                List<String> line_lines = Files.lines(Paths.get(lfile.getAbsolutePath())).collect(Collectors.toList());
                //ArrayList<InvoiceHedear> invoic = new ArrayList<>();
                for(String header_line : header_lines){
             String[] spliter_array = header_line.split(",");
             String nums = spliter_array[0];
             String dates = spliter_array[1];
             String names = spliter_array[2];
             int number = Integer.parseInt(nums);
             Date date = NewJFrame_View.da.parse(dates);
             InvoiceHedear i= new InvoiceHedear(number,names,date);
             fram.getInvoic().add(i);
             //fram.getInvoic().add(i);
             //invoic.add(i);
             
           }
                System.out.println("out");
        for (String line_line : line_lines){
            String[] split_array = line_line.split(",");
            int numb = Integer.parseInt(split_array[0]);
            String name = split_array[1];
            double price =Double.parseDouble(split_array[2]);
            int c = Integer.parseInt(split_array[3]);
            InvoiceHedear invo = getinvoice_number(numb);
            InvoiceLine line = new InvoiceLine(name, price, c , invo);
            invo.getLines().add(line);
            
        }
        System.out.println("check!!");
        fram.gettable_invoice().setModel(new HeaderModel(fram.getInvoic()));
            }catch(Exception x){
                x.printStackTrace();
            }
        }
    }
    private InvoiceHedear getinvoice_number(int numb){
        for (InvoiceHedear inv : fram.getInvoic()){
            if (numb == inv.getNumber()){
                return inv;
            }
        }
        return null;
    }
    private void save() throws IOException {
               // HeaderModel headermodel = (HeaderModel) fram.gettable_line().getModel();
            HeaderModel headermodel = (HeaderModel) fram.gettable_invoice().getModel();
            for (int i = 0; i < headermodel.getinvoice().size();++i) {
                System.out.println(headermodel.getinvoice().get(i).gettotal());
                System.out.println(headermodel.getinvoice().get(i).getCustomer_name());            
            }
            
          try (FileWriter writer = new FileWriter("test1.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invoice Number,Customer Name,Invoice Date,Invoice Total" + '\n');

            for (int i = 0; i < headermodel.getinvoice().size(); i++) {
                
               // sb.append(headermodel.getRowCount().get(i).getInvoice().getNumber());
               // sb.append(',');
                sb.append(headermodel.getinvoice().get(i).getNumber());
                sb.append(',');
                sb.append(headermodel.getinvoice().get(i).getCustomer_name());
                sb.append(',');
                sb.append(headermodel.getinvoice().get(i).getDate());
                sb.append(',');
                sb.append(headermodel.getinvoice().get(i).gettotal());
                sb.append('\n');
            
            }

            writer.write(sb.toString());
            writer.flush();
            writer.close();
            

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        LineModel linemodel = (LineModel) fram.gettable_line().getModel();
        
        for (int i = 0; i < linemodel.getLine().size(); i++) {
            System.out.println(linemodel.getLine().get(i).getItem());
                      
        }
        try (FileWriter writer = new FileWriter("test.csv")) {
            StringBuilder sb = new StringBuilder();
           // sb.append("ItemName,ItemPrice,Count,ItemTotal" + '\n');

            for (int i = 0; i < linemodel.getLine().size(); i++) {
               sb.append(linemodel.getLine().get(i).getInvoice().getNumber());
                sb.append(',');
                sb.append(linemodel.getLine().get(i).getItem());
                sb.append(',');
                sb.append(linemodel.getLine().get(i).getInv_prices());
                sb.append(',');
                sb.append(linemodel.getLine().get(i).getInv_count());
                sb.append(',');
               // sb.append(linemodel.getLine().get(i).getTotal());
                sb.append('\n');
            }

            writer.write(sb.toString());
            writer.flush();
            writer.close();
            

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
         
    
    }

   /* private void Invoice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    private int getnextnum(){
        int numb = 1;
        for (InvoiceHedear invoi :fram.getInvoic()){
            if (invoi.getNumber() > numb){
               numb = invoi.getNumber();
            }
        }
        return numb +1;
        
    }

    private void SubmitTheInvoice() {
        int n = getnextnum();
        String customername = newheader.getNameField().getText();
        String date = newheader.getDateField().getText();
        newheader.setVisible(false);
        newheader.dispose();
        try{
        Date dates = fram.da.parse(date);
        InvoiceHedear in = new InvoiceHedear(n, customername, dates);
        fram.getInvoic().add(in);
        ((HeaderModel)fram.gettable_invoice().getModel()).fireTableDataChanged();

        

        }catch(ParseException ex){
            JOptionPane.showMessageDialog(fram, "Get An Errorr Formating for date","Error Message",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }

    private void CancelTheInvoice() {
        newheader.setVisible(false);
        newheader.dispose();
    }


    private void SubmitTheNewLine() {
        String itemname = newlines.getNamel().getText();
        String count = newlines.getCountl().getText();
        String price = newlines.getPricel().getText();

        int r =  fram.gettable_invoice().getSelectedRow();
        
        newlines.setVisible(false);
        newlines.dispose();
        int c = Integer.parseInt(count);
        double p = Double.parseDouble(price);
        
        InvoiceLine in = new InvoiceLine(itemname, p, c, fram.getInvoic().get(r));
       // System.out.println(invoice.getNumber());

        LineModel linemodel = (LineModel) fram.gettable_line().getModel();
        linemodel.getLine().add(in);
        linemodel.fireTableDataChanged();

        for (int i = 0; i < linemodel.getLine().size(); i++) {
            System.out.println(linemodel.getLine().get(i).getItem());
        }

        
        }
 
    private void CancelTheNewLine() {
        newlines.setVisible(true);
        newlines.dispose();
    }
    
}
