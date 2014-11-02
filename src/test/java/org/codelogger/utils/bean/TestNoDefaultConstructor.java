package org.codelogger.utils.bean;

public class TestNoDefaultConstructor {

    private final String name;

    public TestNoDefaultConstructor(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
