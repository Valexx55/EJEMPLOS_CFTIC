package com.example.ejemploscftic.dto;


public class UsrDto {

    private String mail;
    private String pwd;

    public UsrDto(String mail, String pwd) {
        this.mail = mail;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "MAIL " + mail + " validado";
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
