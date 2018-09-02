package com.example.paras.myvote;

/**
 * Created by Paras on 08/01/2018.
 */

public class Candidate {
    private String name;
    private String partyName;

    public Candidate() {
    }

    public Candidate(String name, String partyName) {
        this.name = name;
        this.partyName = partyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}
