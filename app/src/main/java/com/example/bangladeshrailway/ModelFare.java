package com.example.bangladeshrailway;

public class ModelFare {
    String AC_B,AC_S,S_CHAIR,SHOVON,SNIGDHA;

    public String getAC_B() {
        return AC_B;
    }

    public void setAC_B(String AC_B) {
        this.AC_B = AC_B;
    }

    public String getAC_S() {
        return AC_S;
    }

    public void setAC_S(String AC_S) {
        this.AC_S = AC_S;
    }

    public String getS_CHAIR() {
        return S_CHAIR;
    }

    public void setS_CHAIR(String s_CHAIR) {
        S_CHAIR = s_CHAIR;
    }

    public String getSHOVON() {
        return SHOVON;
    }

    public void setSHOVON(String SHOVON) {
        this.SHOVON = SHOVON;
    }

    public String getSNIGDHA() {
        return SNIGDHA;
    }

    public void setSNIGDHA(String SNIGDHA) {
        this.SNIGDHA = SNIGDHA;
    }

    public ModelFare(String AC_B, String AC_S, String s_CHAIR, String SHOVON, String SNIGDHA) {
        this.AC_B = AC_B;
        this.AC_S = AC_S;
        S_CHAIR = s_CHAIR;
        this.SHOVON = SHOVON;
        this.SNIGDHA = SNIGDHA;
    }

    public ModelFare() {
    }
}
