/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BytesManager;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectType;
import opencellar.framework.Utils;

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
        return super.getLength() + 430;
    }

    public final void setName(String s) {
        this.m_name = Utils.ensureCapacity(s, 60);
        super.setToUpdate();
    }

    public final String getName() {
        return this.m_name;
    }

    public final String getAddress1() {
        return this.m_address1;
    }

    public final void setAddress1(String s) {
        this.m_address1 = Utils.ensureCapacity(s, 100);
        super.setToUpdate();
    }

    public final String getAddress2() {
        return this.m_address2;
    }

    public final void setAddress2(String s) {
        this.m_address2 = Utils.ensureCapacity(s, 100);
        super.setToUpdate();
    }

    public final String getWeb() {
        return this.m_web;
    }

    public final void setWeb(String s) {
        this.m_web = Utils.ensureCapacity(s, 60);
        super.setToUpdate();
    }

    public final String getPhone() {
        return this.m_phone;
    }

    public final void setPhone(String s) {
        this.m_phone = Utils.ensureCapacity(s, 20);
        super.setToUpdate();
    }

    public final String getFax() {
        return this.m_fax;
    }

    public final void setFax(String s) {
        this.m_fax = Utils.ensureCapacity(s, 20);
        super.setToUpdate();
    }

    public final String getEmail() {
        return this.m_email;
    }

    public final void setEmail(String s) {
        this.m_email = Utils.ensureCapacity(s, 30);
        super.setToUpdate();
    }

    public final String getZipCode() {
        return this.m_zipCode;
    }

    public final void setZipCode(String s) {
        this.m_zipCode = Utils.ensureCapacity(s, 10);
        super.setToUpdate();
    }

    public final String getCity() {
        return this.m_city;
    }

    public final void setCity(String s) {
        this.m_city = Utils.ensureCapacity(s, 30);
        super.setToUpdate();
    }

    public final String toString() {
        return this.m_name.trim();
    }

    protected final void write(BytesManager m) {
        super.write(m);
        m.write(Utils.padRight(this.getName(), 60, " "));
        m.write(Utils.padRight(this.getAddress1(), 100, " "));
        m.write(Utils.padRight(this.getAddress2(), 100, " "));
        m.write(Utils.padRight(this.getPhone(), 20, " "));
        m.write(Utils.padRight(this.getFax(), 20, " "));
        m.write(Utils.padRight(this.getEmail(), 30, " "));
        m.write(Utils.padRight(this.getZipCode(), 10, " "));
        m.write(Utils.padRight(this.getCity(), 30, " "));
        m.write(Utils.padRight(this.getWeb(), 60, " "));
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.setName(m.getString(60));
        this.setAddress1(m.getString(100));
        this.setAddress2(m.getString(100));
        this.setPhone(m.getString(20));
        this.setFax(m.getString(20));
        this.setEmail(m.getString(30));
        this.setZipCode(m.getString(10));
        this.setCity(m.getString(30));
        this.setWeb(m.getString(60));
    }
}

