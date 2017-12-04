package com.weblogin.beans;

import java.io.Serializable;

public class CredentialBean implements Serializable {

    private static final long serialVersionUID = 8057916645209523507L;
    private String hash;
    private String salt;

    public CredentialBean(){

    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}