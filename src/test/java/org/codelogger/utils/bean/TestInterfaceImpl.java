package org.codelogger.utils.bean;

public class TestInterfaceImpl implements TestInterface {

    private String name;

    public TestInterfaceImpl() {

    }

    public TestInterfaceImpl(String name) {

        this.name = name;
    }

    public String hello() {

        return "Hello: " + name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

}
