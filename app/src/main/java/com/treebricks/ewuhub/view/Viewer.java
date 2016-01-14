package com.treebricks.ewuhub.view;

public class Viewer
{
    private Object vHeader;
    private Object vFirstCourse;
    private Object vSecondCourse;
    private Object vThirdCourse;
    private Object vFourthCourse;

    public Viewer(Object vHeader, Object vFirstCourse, Object vSecondCourse, Object vThirdCourse, Object vFourthCourse) {
        this.vHeader = vHeader;
        this.vFirstCourse = vFirstCourse;
        this.vSecondCourse = vSecondCourse;
        this.vThirdCourse = vThirdCourse;
        this.vFourthCourse = vFourthCourse;
    }

    public Viewer(Object vHeader, Object vFirstCourse, Object vSecondCourse, Object vThirdCourse) {
        this.vHeader = vHeader;
        this.vFirstCourse = vFirstCourse;
        this.vSecondCourse = vSecondCourse;
        this.vThirdCourse = vThirdCourse;
        vFourthCourse = "NULL";
    }

    public Object getvHeader() {
        return vHeader;
    }

    public void setvHeader(Object vHeader) {
        this.vHeader = vHeader;
    }

    public Object getvFirstCourse() {
        return vFirstCourse;
    }

    public void setvFirstCourse(Object vFirstCourse) {
        this.vFirstCourse = vFirstCourse;
    }

    public Object getvSecondCourse() {
        return vSecondCourse;
    }

    public void setvSecondCourse(Object vSecondCourse) {
        this.vSecondCourse = vSecondCourse;
    }

    public Object getvThirdCourse() {
        return vThirdCourse;
    }

    public void setvThirdCourse(Object vThirdCourse) {
        this.vThirdCourse = vThirdCourse;
    }

    public Object getvFourthCourse() {
        return vFourthCourse;
    }

    public void setvFourthCourse(Object vFourthCourse) {
        this.vFourthCourse = vFourthCourse;
    }
}
