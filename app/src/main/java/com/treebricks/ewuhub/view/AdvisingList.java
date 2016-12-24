package com.treebricks.ewuhub.view;

/**
 * Created by fahim on 10/25/16.
 */

public class AdvisingList {
    String courseName;
    String courseSection;
    String courseTimefrom;
    String courseTimeto;
    String courseWeekday;
    String courseFaculty;
    String courseRoom;

    public AdvisingList() {
    }

    public AdvisingList(String courseName, String courseSection, String courseTimefrom,
                        String courseTimeto, String courseWeekday, String courseFaculty, String courseRoom) {
        this.courseName = courseName;
        this.courseSection = courseSection;
        this.courseTimefrom = courseTimefrom;
        this.courseTimeto = courseTimeto;
        this.courseWeekday = courseWeekday;
        this.courseFaculty = courseFaculty;
        this.courseRoom = courseRoom;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    public String getCourseTimefrom() {
        return courseTimefrom;
    }

    public void setCourseTimefrom(String courseTimefrom) {
        this.courseTimefrom = courseTimefrom;
    }

    public String getCourseTimeto() {
        return courseTimeto;
    }

    public void setCourseTimeto(String courseTimeto) {
        this.courseTimeto = courseTimeto;
    }

    public String getCourseWeekday() {
        return courseWeekday;
    }

    public void setCourseWeekday(String courseWeekday) {
        this.courseWeekday = courseWeekday;
    }

    public String getCourseFaculty() {
        return courseFaculty;
    }

    public void setCourseFaculty(String courseFaculty) {
        this.courseFaculty = courseFaculty;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

    @Override
    public String toString() {
        return "AdvisingListViewer{" +
                "courseName='" + courseName + '\'' +
                ", courseSection='" + courseSection + '\'' +
                ", courseTimefrom='" + courseTimefrom + '\'' +
                ", courseTimeto='" + courseTimeto + '\'' +
                ", courseWeekday='" + courseWeekday + '\'' +
                ", courseFaculty='" + courseFaculty + '\'' +
                ", courseRoom='" + courseRoom + '\'' +
                '}';
    }
}
