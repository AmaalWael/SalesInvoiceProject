/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesinvoiceproject.InvView;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author Wael
 */
public class NewLines extends JDialog{
    private JTextField namel;
    private JTextField countl;
    private JTextField pricel;
    private JLabel namelable;
    private JLabel countlabel;
    private JLabel pricelabel;
    private JButton submitlabel;
    private JButton cancellabel;
    
    
public NewLines (NewJFrame_View fram){
    namelable = new JLabel("Item Name");
    namel = new JTextField(30);
    countlabel = new JLabel("Item Count");
    countl =new JTextField(30);
    pricelabel = new JLabel("Item Price");
    pricel = new JTextField(15);
    submitlabel = new JButton("Submit");
    cancellabel = new JButton("Cancel");
    submitlabel.setActionCommand("Submit The NewLine");
    cancellabel.setActionCommand("Cancel The NewLine");
    submitlabel.addActionListener(fram.getListener());
    cancellabel.addActionListener(fram.getListener());
    
    
    
    setLayout(new GridLayout(4, 2));
    add(namel);
    add(namelable);
    add(countl);
    add(countlabel);
    add(pricel);
    add(pricelabel);
    add(submitlabel);
    add(cancellabel);
    setModal(true);
    pack();

    
}

    public JTextField getNamel() {
        return namel;
    }

    public void setNamel(JTextField namel) {
        this.namel = namel;
    }

    public JTextField getCountl() {
        return countl;
    }

    public void setCountl(JTextField countl) {
        this.countl = countl;
    }

    public JTextField getPricel() {
        return pricel;
    }

    public void setPricel(JTextField pricel) {
        this.pricel = pricel;
    }

    public JLabel getNamelable() {
        return namelable;
    }

    public void setNamelable(JLabel namelable) {
        this.namelable = namelable;
    }

    public JLabel getCountlabel() {
        return countlabel;
    }

    public void setCountlabel(JLabel countlabel) {
        this.countlabel = countlabel;
    }

    public JLabel getPricelabel() {
        return pricelabel;
    }

    public void setPricelabel(JLabel pricelabel) {
        this.pricelabel = pricelabel;
    }

    public JButton getSubmitlabel() {
        return submitlabel;
    }

    public void setSubmitlabel(JButton submitlabel) {
        this.submitlabel = submitlabel;
    }

    public JButton getCancellabel() {
        return cancellabel;
    }

    public void setCancellabel(JButton cancellabel) {
        this.cancellabel = cancellabel;
    }

    
}
