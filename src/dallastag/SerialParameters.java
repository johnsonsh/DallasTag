/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dallastag;
import gnu.io.*;

/**
 *
 * @author Shaheen
 */
public class SerialParameters {
    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int flowCntrl;
    private int parity;

  
    /**
     * Default Serial Port Parameters
     */
    public SerialParameters () {
        this.portName = "COM1";
        this.baudRate = 9600;
        this.dataBits = SerialPort.DATABITS_8;
        this.stopBits = SerialPort.STOPBITS_1;
        this.flowCntrl = SerialPort.FLOWCONTROL_NONE;
        this.parity = SerialPort.PARITY_NONE;
    }
    /**
     * Parameterized Constructor
     * @param portName
     * @param baudRate
     * @param dataBits
     * @param stopBits
     * @param flowCntrl
     * @param parity 
     */    
    public SerialParameters (   String portName, 
                                int baudRate, 
                                int dataBits, 
                                int stopBits, 
                                int flowCntrl, 
                                int parity) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.flowCntrl = flowCntrl;
        this.parity = parity;
    }
    
      public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getDataBits() {
        return dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public int getFlowCntrl() {
        return flowCntrl;
    }

    public void setFlowCntrl(int flowCntrl) {
        this.flowCntrl = flowCntrl;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }
}
