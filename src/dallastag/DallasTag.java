/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dallastag;

import gnu.io.NoSuchPortException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Shaheen
 */
public class DallasTag extends javax.swing.JFrame {
    
    private SerialParameters parameters;
    private SerialConnection connection;
    
    private String portSelect;
    private int baudSelect;
    private boolean err;
    
    private FileWriter fStream;
    private BufferedWriter bWrite;
    
    private ArrayList textList;
    private DefaultCaret caret;
    
    JFileChooser fileDialog;
    ImageIcon logo = new ImageIcon("maxi.png");
    Image image;
    ImageIcon icon;
    
    

    /**
     * Creates new form DallasTag
     */
    public DallasTag() {
        super("Dallas Tag Reader");
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("black-icon.png"));
        icon = new ImageIcon(image);
        initComponents();
        
        caret = (DefaultCaret) textAreaOut.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        parameters = new SerialParameters();
        connection = new SerialConnection(this, parameters, textAreaOut);
        err = false;
        closePortButton.setEnabled(false);
        /*textAreaOut.append( "000000FBC52C\r\n" +
                            "0000003B234B\r\n" +
                            "000000D5C56B\r\n" +
                            "0000001BD22F\r\n" +
                            "0000008B111B\r\n" +
                            "0000003E6430\r\n" +
                            "00000067082C\r\n" +
                            "000000AB832A\r\n" +
                            "00000082CD4C\r\n" );*/
        textList = new ArrayList();
        windowsLookMenuItem.setEnabled(false);      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saveFileChooser = new javax.swing.JFileChooser();
        comOptionsPanel = new javax.swing.JPanel();
        comPortLabel = new javax.swing.JLabel();
        comPortOptions = new javax.swing.JComboBox<>();
        baudRateLabel = new javax.swing.JLabel();
        baudRateOptions = new javax.swing.JComboBox<>();
        textPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaOut = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        openPortButton = new javax.swing.JButton();
        closePortButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        clearMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        windowsLookMenuItem = new javax.swing.JMenuItem();
        swingLookMenuItem = new javax.swing.JMenuItem();
        nimbusLookMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        saveFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveFileChooser.setApproveButtonText("Save");
        saveFileChooser.setDialogTitle("Save to CSV");
        saveFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileChooserActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(icon.getImage());
        setLocation(new java.awt.Point(480, 320));
        setResizable(false);

        comOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Comm Settings"));

        comPortLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comPortLabel.setText("Comm Port");

        comPortOptions.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comPortOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COM1", "COM2", "COM3", "COM4" }));
        comPortOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comPortOptionsActionPerformed(evt);
            }
        });

        baudRateLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        baudRateLabel.setText("Baud Rate");

        baudRateOptions.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        baudRateOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9600", "19200", "38400", "115200" }));

        javax.swing.GroupLayout comOptionsPanelLayout = new javax.swing.GroupLayout(comOptionsPanel);
        comOptionsPanel.setLayout(comOptionsPanelLayout);
        comOptionsPanelLayout.setHorizontalGroup(
            comOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comOptionsPanelLayout.createSequentialGroup()
                .addGroup(comOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comPortLabel)
                    .addComponent(baudRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(comOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comPortOptions, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(baudRateOptions, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        comOptionsPanelLayout.setVerticalGroup(
            comOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(comOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comPortLabel)
                    .addComponent(comPortOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(comOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baudRateLabel)
                    .addComponent(baudRateOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dallas Tag Numbers"));
        textPanel.setAutoscrolls(true);

        textAreaOut.setEditable(false);
        textAreaOut.setColumns(20);
        textAreaOut.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        textAreaOut.setRows(5);
        jScrollPane1.setViewportView(textAreaOut);

        javax.swing.GroupLayout textPanelLayout = new javax.swing.GroupLayout(textPanel);
        textPanel.setLayout(textPanelLayout);
        textPanelLayout.setHorizontalGroup(
            textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );
        textPanelLayout.setVerticalGroup(
            textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        buttonPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        openPortButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        openPortButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Shaheen\\Documents\\NetBeansProjects\\DallasTag\\src\\dallastag\\checked.png")); // NOI18N
        openPortButton.setText("Init Port");
        openPortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openPortButtonActionPerformed(evt);
            }
        });

        closePortButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        closePortButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Shaheen\\Documents\\NetBeansProjects\\DallasTag\\src\\dallastag\\cancel.png")); // NOI18N
        closePortButton.setText("Exit Port");
        closePortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closePortButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openPortButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closePortButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openPortButton)
                    .addComponent(closePortButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        clearMenuItem.setText("Clear Window");
        clearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(clearMenuItem);

        menuBar.add(editMenu);

        viewMenu.setText("View");

        windowsLookMenuItem.setText("Windows Look");
        windowsLookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                windowsLookMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(windowsLookMenuItem);

        swingLookMenuItem.setText("Swing Look");
        swingLookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swingLookMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(swingLookMenuItem);

        nimbusLookMenuItem.setText("Nimbus Look");
        nimbusLookMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nimbusLookMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(nimbusLookMenuItem);

        menuBar.add(viewMenu);

        helpMenu.setText("Help");
        helpMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuActionPerformed(evt);
            }
        });

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comOptionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comPortOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comPortOptionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comPortOptionsActionPerformed

    private void openPortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openPortButtonActionPerformed
        err = false;
        openPortButton.setEnabled(false);
        parameters.setPortName((String) comPortOptions.getItemAt(comPortOptions.getSelectedIndex()));
        parameters.setBaudRate((int) Integer.parseInt(baudRateOptions.getItemAt(baudRateOptions.getSelectedIndex())));
                
        try {
            connection.openConnection();
        } catch (NoSuchPortException ex) {
            //System.out.println("No Such Port " + ex.getMessage());
            //err = true;
            //System.out.println(err);
            //System.exit(1);
            JOptionPane.showMessageDialog(this, "No Such Port: " + parameters.getPortName(),
                "Error", JOptionPane.ERROR_MESSAGE );            
            
            System.out.println("No Such Port " + ex.getMessage());
            return;
                 
            
            //openPortButton.setEnabled(true);
        }        
        if(!err) {
            //System.out.println("Com Port: " + parameters.getPortName() + " is open at " + parameters.getBaudRate());
            JOptionPane.showMessageDialog(this, parameters.getPortName() + " is open.",
                "Port Status", JOptionPane.INFORMATION_MESSAGE );
            
            portOpened();
        } else {
            openPortButton.setEnabled(true);
            comPortOptions.setEnabled(true);
            baudRateOptions.setEnabled(true);
            //err = false;
        }
    }//GEN-LAST:event_openPortButtonActionPerformed

    private void closePortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closePortButtonActionPerformed
        if(connection.isOpen()){
            connection.closeConnection();
            JOptionPane.showMessageDialog(this, parameters.getPortName() + " is closed.",
                "Port Status", JOptionPane.INFORMATION_MESSAGE );
            portClosed();
        } else {
            JOptionPane.showMessageDialog(this, "Port is not open.",
                "Port Status", JOptionPane.INFORMATION_MESSAGE );
        }
    }//GEN-LAST:event_closePortButtonActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        Object event = evt.getSource();
     
        if(event == saveMenuItem){
            File file;
            FileFilter ff;
            FileNameExtensionFilter fnef;
            
            fnef = new FileNameExtensionFilter("CSV (Comma Delimited) (*.csv)", "csv");
           
            //System.out.println("Save Item");
            
            fileDialog = new JFileChooser();
            fileDialog.setFileFilter(new FileNameExtensionFilter("CSV (Comma Delimited) (*.csv)", "csv"));
            //fileDialog.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.txt)", "txt"));
            //fileDialog.setSelectedFile(new File("export.csv"));
            String [] str = fnef.getExtensions();
            file = new File("export." + str[0]);
            fileDialog.setSelectedFile(file);
            fileDialog.setCurrentDirectory(file);
                                                     
            int c = fileDialog.showSaveDialog(this);
            if(c == JFileChooser.APPROVE_OPTION) {
                try {
                    //System.out.println(fileDialog.getSelectedFile().getName());
                    //System.out.println(fileDialog.getSelectedFile().getAbsolutePath());
                    String s[] = textAreaOut.getText().split("\\r?\\n");
                    textList = new ArrayList(Arrays.asList(s));
                    this.writeFile(fileDialog.getSelectedFile().getAbsolutePath(), textList);
                    
                } catch (IOException ex) {
                    //Logger.getLogger(DallasTag.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Cannot write to file. File already open.",
                            "Error", JOptionPane.ERROR_MESSAGE );
                }
            } else {
                //System.out.println("Cancel Button Pressed");
            }
        }        
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        Object event = evt.getSource();
        if(event == exitMenuItem) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void saveFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileChooserActionPerformed
        Object event = evt.getSource();        
    }//GEN-LAST:event_saveFileChooserActionPerformed

    private void swingLookMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swingLookMenuItemActionPerformed
        Object event = evt.getSource();
        if(event == swingLookMenuItem){
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch(Exception ee){ }
        }
        swingLookMenuItem.setEnabled(false);
        windowsLookMenuItem.setEnabled(true);
        nimbusLookMenuItem.setEnabled(true);
    }//GEN-LAST:event_swingLookMenuItemActionPerformed

    private void windowsLookMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_windowsLookMenuItemActionPerformed
        Object event = evt.getSource();
        if(event == windowsLookMenuItem){
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ee){ }
        }
        swingLookMenuItem.setEnabled(true);
        windowsLookMenuItem.setEnabled(false);
        nimbusLookMenuItem.setEnabled(true);
    }//GEN-LAST:event_windowsLookMenuItemActionPerformed

    private void nimbusLookMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nimbusLookMenuItemActionPerformed
        Object event = evt.getSource();
        if(event == nimbusLookMenuItem){
            try {
                
                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.Wi‌​ndowsClassicLookAndF‌​eel");
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch(Exception ee){ }
        }
        swingLookMenuItem.setEnabled(true);
        windowsLookMenuItem.setEnabled(true);
        nimbusLookMenuItem.setEnabled(false);
    }//GEN-LAST:event_nimbusLookMenuItemActionPerformed

    private void clearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearMenuItemActionPerformed
        Object event = evt.getSource();
        if(event == clearMenuItem){
            textAreaOut.setText(null);
        }
    }//GEN-LAST:event_clearMenuItemActionPerformed

    private void helpMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuActionPerformed
        Object event = evt.getSource();
        if(event == helpMenu){
            JOptionPane.showMessageDialog(this, "FTP Error",
                "FTP Error", JOptionPane.ERROR_MESSAGE );
        }
    }//GEN-LAST:event_helpMenuActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        Object event = evt.getSource();
        if(event == aboutMenuItem){
            JOptionPane.showMessageDialog(this, "Dallas Tag Reaader v1.00\nMaxi Technologies 2017",
                "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    public void portOpened() {
	openPortButton.setEnabled(false);
	closePortButton.setEnabled(true);
        comPortOptions.setEnabled(false);
        baudRateOptions.setEnabled(false);
    }
    
    public void portClosed() {
	openPortButton.setEnabled(true);
	closePortButton.setEnabled(false);
        comPortOptions.setEnabled(true);
        baudRateOptions.setEnabled(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DallasTag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DallasTag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DallasTag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DallasTag.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DallasTag dt = new DallasTag();
                dt.setLocationRelativeTo(null);
                dt.setVisible(true);
            }
        });
    }
    
    public boolean getErr(){
        return this.err;
    }
    
    public void setErr(boolean err){
        this.err = err;
    }
    
    public final void writeFile(String fileName) throws IOException {
	fStream = new FileWriter(fileName);
	bWrite = new BufferedWriter(fStream);
		
	try {
            bWrite.write("Hello World!");
	}
		
	finally {
            bWrite.close();
	}
    }
    
    public final void writeFile(String fileName, String data) throws IOException {
	fStream = new FileWriter(fileName);
	bWrite = new BufferedWriter(fStream);
		
	try {
            bWrite.write(data);
	}
		
	finally {
            bWrite.close();
	}
    }
    
    public final void writeFile(String fileName, ArrayList data) throws IOException {
        fStream = new FileWriter(fileName);
	bWrite = new BufferedWriter(fStream);
		
	try {
            for(int idx = 0; idx < data.size(); idx++) {
                bWrite.write((idx+1) + "," + (String) data.get(idx) + "," + "\n");
            }
	}
	finally {
            bWrite.close();
	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel baudRateLabel;
    private javax.swing.JComboBox<String> baudRateOptions;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JMenuItem clearMenuItem;
    private javax.swing.JButton closePortButton;
    private javax.swing.JPanel comOptionsPanel;
    private javax.swing.JLabel comPortLabel;
    private javax.swing.JComboBox<String> comPortOptions;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem nimbusLookMenuItem;
    private javax.swing.JButton openPortButton;
    private javax.swing.JFileChooser saveFileChooser;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem swingLookMenuItem;
    private javax.swing.JTextArea textAreaOut;
    private javax.swing.JPanel textPanel;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JMenuItem windowsLookMenuItem;
    // End of variables declaration//GEN-END:variables
}
