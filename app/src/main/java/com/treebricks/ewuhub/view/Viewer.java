package com.treebricks.ewuhub.view;

public class Viewer
{
    private String vHeader;
    private String vFirstCourse;
    private String vSecondCourse;
    private String vThirdCourse;
    private String vFourthCourse;

    public Viewer(String vHeader, String vFirstCourse, String vSecondCourse, String vThirdCourse)
    {
        this.vHeader = vHeader;
        this.vFirstCourse = vFirstCourse;
        this.vSecondCourse = vSecondCourse;
        this.vThirdCourse = vThirdCourse;
    }

    public String getvHeader() {
        return vHeader;
    }

    public void setvHeader(String vHeader) {
        this.vHeader = vHeader;
    }

    public String getvFirstCourse() {
        return vFirstCourse;
    }

    public void setvFirstCourse(String vFirstCourse) {
        this.vFirstCourse = vFirstCourse;
    }

    public String getvSecondCourse() {
        return vSecondCourse;
    }

    public void setvSecondCourse(String vSecondCourse) {
        this.vSecondCourse = vSecondCourse;
    }

    public String getvThirdCourse() {
        return vThirdCourse;
    }

    public void setvThirdCourse(String vThirdCourse) {
        this.vThirdCourse = vThirdCourse;
    }

    public String getvFourthCourse() {
        return vFourthCourse;
    }

    public void setvFourthCourse(String vFourthCourse) {
        this.vFourthCourse = vFourthCourse;
    }
}
