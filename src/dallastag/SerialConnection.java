/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dallastag;
import gnu.io.*;
import java.io.*;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.util.TooManyListenersException;
import javax.swing.JOptionPane;
/**
 *
 * @author Shaheen
 */
public class SerialConnection implements SerialPortEventListener,
                                         CommPortOwnershipListener {
    private DallasTag parent;
    
    private JTextArea textAreaOut;
    
    private SerialParameters parameters;
    private OutputStream out;
    private InputStream in;
    private CommPortIdentifier portId;
    private SerialPort sPort;
    StringBuffer inputBuffer;
    
    private boolean open;
    private boolean err;
    
    /**
     * 
     */
    public SerialConnection ( ) {
        
    }
    /**
     * Parameterized Constructor
     * @param parent DallasTag GUI object
     * @param parameters SerialParameters object
     * @param textAreaOut text area that displays serial data out
     */
    public SerialConnection ( DallasTag parent,
            SerialParameters parameters,
            JTextArea textAreaOut ) {
        
        this.parent = parent;
        this.parameters = parameters;
        this.textAreaOut = textAreaOut;
        this.open = false;
        this.err = false;
        this.inputBuffer = new StringBuffer();
    }
    
    /**
     * 
     * @throws NoSuchPortException 
     */
    public void openConnection() throws NoSuchPortException {
        try {
            portId = CommPortIdentifier.getPortIdentifier(parameters.getPortName());
        } catch (NoSuchPortException nspe) {
            //System.out.println("No Such Port " + nspe.getMessage());
            err = true;
            //System.out.println(err);
            //System.exit(1);
            JOptionPane.showMessageDialog(parent, "No Such Port: " + parameters.getPortName(),
                "Error", JOptionPane.ERROR_MESSAGE );
            parent.setErr(true);
            return;
            
        }
        
        if(!parent.getErr()) {
            try {
                sPort = (SerialPort) portId.open("DallasTag", 30000);
            } catch (PortInUseException piue) {
                //System.out.println("Port In Use " + piue.getMessage());
                //System.exit(1);
                JOptionPane.showMessageDialog(parent, "Port in use: " + parameters.getPortName(),
                    "Error", JOptionPane.ERROR_MESSAGE );
            }
        
            try {
                setConnectionParameters();
            } catch (Exception e) {
                System.out.println("Serial Parameters" + e.getMessage());
            }
        
            try {
                out = sPort.getOutputStream();
                in = sPort.getInputStream();
            } catch (IOException e) {
                sPort.close();
            }
        
            try {
                sPort.addEventListener(this);
            } catch (TooManyListenersException e) {
                sPort.close();
                System.out.println("Too Many Listeners" + e.getMessage());
            }
        
            // Set notifyOnDataAvailable to true to allow event driven input.
            sPort.notifyOnDataAvailable(true);

            // Set notifyOnBreakInterrup to allow event driven break handling.
            sPort.notifyOnBreakInterrupt(true);

            // Set receive timeout to allow breaking out of polling loop during
            // input handling.
            try {
                sPort.enableReceiveTimeout(30);
            } catch (UnsupportedCommOperationException e) {
            }

            // Add ownership listener to allow ownership event handling.
            portId.addPortOwnershipListener(this);
            open = true;
        }
    }
    
    public void setConnectionParameters() {

	// Save state of parameters before trying a set.
	int oldBaudRate = sPort.getBaudRate();
	int oldDatabits = sPort.getDataBits();
	int oldStopbits = sPort.getStopBits();
	int oldParity   = sPort.getParity();
	int oldFlowCntrl = sPort.getFlowControlMode();

	// Set connection parameters, if set fails return parameters object
	// to original state.
	try {
	    sPort.setSerialPortParams(parameters.getBaudRate(),
				      parameters.getDataBits(),
				      parameters.getStopBits(),
				      parameters.getParity());
	} catch (UnsupportedCommOperationException e) {
	    parameters.setBaudRate(oldBaudRate);
	    parameters.setDataBits(oldDatabits);
	    parameters.setStopBits(oldStopbits);
	    parameters.setParity(oldParity);
            System.out.println("Parameters" + e.getMessage());
	}
    }
    
    /**
     * Close the port and clean up associated elements.
     */
    public void closeConnection() {
	// If port is alread closed just return.
	if (!open) {
	    return;
	}

        // Check to make sure sPort has reference to avoid a NPE.
	if (sPort != null) {
	    try {
		// close the i/o streams.
	    	out.close();
	    	in.close();
	    } catch (IOException e) {
		System.err.println(e);
	    }

	    // Close the port.
	    sPort.close();

	    // Remove the ownership listener.
	    portId.removePortOwnershipListener(this);
	}

	open = false;
    }
    
    /**
    Send a one second break signal.
    */
    public void sendBreak() {
	sPort.sendBreak(1000);
    }
    
    public void sendData232(String data){
        try {
            out.write(data.getBytes());
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean checkOnline(String send, String reply) throws IOException, InterruptedException {
        out.write(send.getBytes());
        out.flush();
                
        if(inputBuffer.toString().matches(reply)){
            return true;    
        }
        else
            return false;
    }
    
    /**
    Reports the open status of the port.
    @return true if port is open, false if port is closed.
    */
    public boolean isOpen() {
	return open;
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        // Create a StringBuffer and int to receive input data.
	StringBuffer inputBuffer = new StringBuffer();
	int newData = 0;

	// Determine type of event.
	switch (spe.getEventType()) {
	    // Read data until -1 is returned. If \r is received substitute
	    // \n for correct newline handling.
	    case SerialPortEvent.DATA_AVAILABLE:
                while (newData != -1) {
                    try {
                        newData = in.read();
			if (newData == -1) {
                            break;
			}
			if ('\r' == (char)newData) {
                            inputBuffer.append('\n');
			} else {
                            inputBuffer.append((char)newData);
			}
		    } catch (IOException ex) {
		        System.err.println(ex);
		        return;
                    }
                }
		// Append received data to messageAreaIn.
		//textAreaOut.append(new String(inputBuffer));
                textAreaOut.append(new String(inputBuffer));
		break;

	    // If break event append BREAK RECEIVED message.
	    case SerialPortEvent.BI:
		//messageAreaIn.append("\n--- BREAK RECEIVED ---\n");            
        }   
    }

    @Override
    public void ownershipChange(int type) {
        if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED) {
	    //PortRequestedDialog prd = new PortRequestedDialog(parent);
	}
    }    
}
