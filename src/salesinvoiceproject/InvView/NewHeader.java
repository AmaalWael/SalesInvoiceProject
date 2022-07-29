/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoiceproject.InvView;

import java.awt.Dialog;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;




/**
 *
 * @author Wael
 */
public class NewHeader extends JDialog {
    private JLabel customerName;
     private JLabel date;
     private JTextField nameField;
     private JTextField dateField;
     private JButton submit;
     private JButton cancel;

public NewHeader (NewJFrame_View fram){
    customerName = new JLabel ("Customer Name");
    nameField = new JTextField(30);
    date = new JLabel ("Date");
    dateField = new JTextField(30);
    submit = new JButton("Submit");
    cancel = new JButton("Cancel");
    submit.setActionCommand("Submit The Invoice");
    cancel.setActionCommand("Cancel The Invoice");
    submit.addActionListener(fram.getListener());
    cancel.addActionListener(fram.getListener());
    setLayout(new GridLayout(3, 2));
    add(customerName);
    add(nameField);
    add(date);
    add(dateField);
    add(submit);
    add(cancel);
    setModal(true);
    pack();
    
}

    public JLabel getCustomerName() {
        return customerName;
    }

    public void setCustomerName(JLabel customerName) {
        this.customerName = customerName;
    }

    public JLabel getDate() {
        return date;
    }

    public void setDate(JLabel date) {
        this.date = date;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public void setDateField(JTextField dateField) {
        this.dateField = dateField;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

    public JButton getCancel() {
        return cancel;
    }

    public void setCancel(JButton cancel) {
        this.cancel = cancel;
    }

     
     
    
    
}
