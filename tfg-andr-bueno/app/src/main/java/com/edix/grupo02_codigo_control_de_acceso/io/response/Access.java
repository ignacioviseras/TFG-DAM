package com.edix.grupo02_codigo_control_de_acceso.io.response;

public class Access{

    private int id, availables, customer_id, event_id;
    private String event_name, event_description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvailables() {
        return availables;
    }

    public void setAvailables(int availables) {
        this.availables = availables;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int user_id) {
        this.customer_id = user_id;
    }

    public void setEvent_name(String event_name) {this.event_name = event_name;}
    public void setEvent_description(String event_description) {this.event_description = event_description;}

    public String getEvent_name() {return this.event_name;}
    public String getEvent_description() {return this.event_description;}

    public void validate() {
        this.availables--;
    }
/*
    public byte[] gerQr() {
        try {
            return QRCodeGenerator.getQRCodeImage(""+this.id,250,250);
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

 */
}