package org.codelogger.utils.bean;

public class TestBeanB {

    private String name;

    private String account;

    private String password;

    private String sort;

    private Long unique;

    public TestBeanB() {

    }

    public TestBeanB(String sort, Long unique) {

        this.sort = sort;
        this.unique = unique;
    }

    @Override
    public String toString() {

        return String.format("Name:%s, Account:%s, Password:%s, Sort:%s, Unique:%s", name, account,
                password, sort, unique);
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

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}
