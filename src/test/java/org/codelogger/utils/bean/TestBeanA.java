package org.codelogger.utils.bean;

public class TestBeanA {

    private String name;

    private String account;

    private String sort;

    private String unique;

    private Long id;

    public TestBeanA() {

    }

    public TestBeanA(String sort, String unique, Long id) {

        this.sort = sort;
        this.unique = unique;
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return this.name;
    }

    public String getAccount() {

        return account;
    }

    public void setAccount(String account) {

        this.account = account;
    }

    @Override
    public String toString() {

        return String.format("Name:%s, Account:%s, Sort:%s, Unique:%s, Id:%s", name, account, sort,
                unique, id);
    }
}
