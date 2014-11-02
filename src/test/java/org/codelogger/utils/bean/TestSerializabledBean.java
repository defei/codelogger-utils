package org.codelogger.utils.bean;

import java.io.Serializable;

public class TestSerializabledBean implements Serializable {

    private static final long serialVersionUID = 8448521033315827234L;

    private String name;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public static long getSerialversionuid() {

        return serialVersionUID;
    }
}
