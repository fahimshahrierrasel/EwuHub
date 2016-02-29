package model;

public class Course
{
    protected String courseName;
    protected int section;
    protected int timeFrom;
    protected int timeTo;
    protected String weekDay;


    public Course()
    {
        courseName = "NULL";
        section = 0;
        timeFrom = 0000;
        timeTo = 0000;
        weekDay = "NULL";
    }

    public Course(String courseName, int section, int timeFrom, int timeTo, String weekDay)
    {
        this.courseName = courseName;
        this.section = section;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.weekDay = weekDay;
    }

    public String getCourseName() {

        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(int timeFrom) {
        this.timeFrom = timeFrom;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(int timeTo) {
        this.timeTo = timeTo;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public boolean identiClassWithLab(String labDay)
    {
        boolean result = false;
        for(int loop1 = 0; loop1 < weekDay.length(); loop1++)
        {
            if (weekDay.charAt(loop1) == labDay.charAt(0))
            {
                result = true;
            }
        }
        return result;
    }
    public boolean identicalClass(Course otherCourse)
    {
        boolean result = false;
        String tempWeekDay = otherCourse.getWeekDay();

        for(int loop1 = 0; loop1 < tempWeekDay.length(); loop1++)
        {
            for(int loop2 = 0; loop2 < weekDay.length(); loop2++)
            {
                if(tempWeekDay.charAt(loop1) == weekDay.charAt(loop2))
                {
                    result = true;
                }
            }
        }

        return result;
    }

    public boolean findConfliction(Course otherCourse)
    {
        boolean result = false;
        if(identicalClass(otherCourse))
        {
            if(otherCourse.timeFrom > timeTo || timeFrom > otherCourse.timeTo)
            {
                result = true;
            }
        }
        else
            result = true;

        return result;
    }

    public String printCourse()
    {
        return (courseName + "     " + section + "     " +
                timeFrom + "     " + timeTo + "     " + weekDay);
    }

    @Override
    public String toString() {
        return courseName + "   Section: " + String.valueOf(section);
    }

    public String courseName()
    {
        return courseName ;
    }
}
