/*
 * Decompiled with CFR 0_122.
 */
package net.spark.cellar;


public class Contact
extends CellarObject {
    static final int LENGTH = 430;
    private String m_name = "";
    private String m_address1;
    private String m_address2;
    private String m_web;
    private String m_phone;
    private String m_fax;
    private String m_email;
    private String m_zipCode;
    private String m_city;

    protected Contact() {
    }

    public ObjectType getType() {
        return ObjectType.None;
    }

    public final int getLength() {
        return 430;
    }

    public final void setName(String s) {
        this.m_name = s;
        
    }

    public final String getName() {
        return this.m_name;
    }

    public final String getAddress1() {
        return this.m_address1;
    }

    public final void setAddress1(String s) {
        this.m_address1 = s;
        
    }

    public final String getAddress2() {
        return this.m_address2;
    }

    public final void setAddress2(String s) {
        this.m_address2 = s;
        
    }

    public final String getWeb() {
        return this.m_web;
    }

    public final void setWeb(String s) {
        this.m_web = s;
        
    }

    public final String getPhone() {
        return this.m_phone;
    }

    public final void setPhone(String s) {
        this.m_phone = s;
        
    }

    public final String getFax() {
        return this.m_fax;
    }

    public final void setFax(String s) {
        this.m_fax = s;
        
    }

    public final String getEmail() {
        return this.m_email;
    }

    public final void setEmail(String s) {
        this.m_email = s;
        
    }

    public final String getZipCode() {
        return this.m_zipCode;
    }

    public final void setZipCode(String s) {
        this.m_zipCode = s;
        
    }

    public final String getCity() {
        return this.m_city;
    }

    public final void setCity(String s) {
        this.m_city = s;
        
    }

    public final String toString() {
        return this.m_name.trim();
    }


}

