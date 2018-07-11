package com.treebricks.ewuhub.model;

public class SortedCourses
{
    private Object vFirstCourse;
    private Object vSecondCourse;
    private Object vThirdCourse;
    private Object vFourthCourse;

    public SortedCourses(Object vFirstCourse, Object vSecondCourse, Object vThirdCourse, Object vFourthCourse) {
        this.vFirstCourse = vFirstCourse;
        this.vSecondCourse = vSecondCourse;
        this.vThirdCourse = vThirdCourse;
        this.vFourthCourse = vFourthCourse;
    }

    public SortedCourses(Object vFirstCourse, Object vSecondCourse, Object vThirdCourse) {
        this.vFirstCourse = vFirstCourse;
        this.vSecondCourse = vSecondCourse;
        this.vThirdCourse = vThirdCourse;
        vFourthCourse = "NULL";
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
