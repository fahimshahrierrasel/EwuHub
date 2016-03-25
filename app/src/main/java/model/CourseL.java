package model;

public class CourseL extends Course
{
    private int labTimeFrom;
    private int labTimeTo;
    private String labWeekDay;

    public CourseL()
    {
        super();
        labTimeFrom = 0;
        labTimeTo = 0;
        labWeekDay = "NULL";

    }
    public CourseL(String courseTitle, int section, int timeFrom, int timeTo, String weekDay, int labTimeFrom, int labTimeTo, String labWeekDay)
    {
        super(courseTitle, section, timeFrom, timeTo, weekDay);
        this.labWeekDay = labWeekDay;
        this.labTimeFrom = labTimeFrom;
        this.labTimeTo = labTimeTo;

    }


    public int getLabTimeFrom() {
        return labTimeFrom;
    }

    public void setLabTimeFrom(int labTimeFrom) {
        this.labTimeFrom = labTimeFrom;
    }

    public int getLabTimeTo() {
        return labTimeTo;
    }

    public void setLabTimeTo(int labTimeTo) {
        this.labTimeTo = labTimeTo;
    }

    public String getLabWeekDay() {
        return labWeekDay;
    }

    public void setLabWeekDay(String labWeekDay) {
        this.labWeekDay = labWeekDay;
    }


    public boolean identicalClass(CourseL otherCourse)
    {
        boolean result = false;
        for(int loop1 = 0; loop1 < otherCourse.weekDay.length(); loop1++)
        {
            for(int loop2 = 0; loop2 < weekDay.length(); loop2++)
            {
                if (weekDay.charAt(loop2) == otherCourse.weekDay.charAt(loop1))
                {
                    result = true;
                }
            }
        }

        return result;
    }



    public boolean findConfliction(CourseL otherCourse)
    {
        boolean result = false;
        if((identicalClass(otherCourse) && identiClassWithLab(otherCourse.labWeekDay)) && (labWeekDay.equals(otherCourse.labWeekDay)))
        {
            if((otherCourse.timeFrom > timeTo || timeFrom > otherCourse.timeTo) && (otherCourse.timeFrom > labTimeTo || labTimeFrom > otherCourse.timeTo) && (otherCourse.labTimeFrom > labTimeTo || labTimeFrom > otherCourse.labTimeTo))
            {
                result = true;
            }

        }
        else if((identicalClass(otherCourse)  && identiClassWithLab(otherCourse.labWeekDay) && !labWeekDay.equals(otherCourse.labWeekDay)))
        {
            if((otherCourse.timeFrom > timeTo || timeFrom > otherCourse.timeTo) && (otherCourse.timeFrom > labTimeTo || labTimeFrom > otherCourse.timeTo))
            {
                result = true;
            }
        }
        else if(identicalClass(otherCourse) && !identiClassWithLab(otherCourse.labWeekDay) && labWeekDay.equals(otherCourse.labWeekDay))
        {
            if((otherCourse.timeFrom > timeTo || timeFrom > otherCourse.timeTo) && (otherCourse.labTimeFrom >labTimeTo || labTimeFrom > otherCourse.labTimeTo))
            {
                result = true;
            }
        }
        else if(!identicalClass(otherCourse) && identiClassWithLab(otherCourse.labWeekDay) && labWeekDay.equals(otherCourse.labWeekDay))
        {
            if((otherCourse.timeFrom > labTimeTo || labTimeFrom > otherCourse.timeTo) && (otherCourse.labTimeFrom >labTimeTo || labTimeFrom > otherCourse.labTimeTo))
            {
                result = true;
            }
        }
        else if(identicalClass(otherCourse) && !identiClassWithLab(otherCourse.labWeekDay) && !labWeekDay.equals(otherCourse.labWeekDay))
        {
            if(otherCourse.timeFrom > timeTo || timeFrom > otherCourse.timeTo)
            {
                result = true;
            }
        }
        else if (!identicalClass(otherCourse) && identiClassWithLab(otherCourse.labWeekDay) && !labWeekDay.equals(otherCourse.labWeekDay))
        {
            if(otherCourse.timeFrom > labTimeTo || labTimeFrom > otherCourse.timeTo)
            {
                result = true;
            }
        }
        else if (!identicalClass(otherCourse) && !identiClassWithLab(otherCourse.labWeekDay) && labWeekDay.equals(otherCourse.labWeekDay))
        {
            if(otherCourse.labTimeFrom > labTimeTo || labTimeFrom > otherCourse.labTimeTo)
            {
                result = true;
            }
        }
        else
            result = true;

        return  result;

    }

    public boolean findConfliction(Course otherCourse)
    {
        boolean result = false;

        //String otherWeekDay = otherCourse.getWeekDay();

        if(identicalClass(otherCourse) && otherCourse.identiClassWithLab(labWeekDay))
        {
            if((otherCourse.getTimeFrom() > timeTo || timeFrom > otherCourse.getTimeTo()) && (otherCourse.getTimeFrom() > labTimeTo || labTimeFrom > otherCourse.getTimeTo()))
            {
                result = true;
            }
        }
        else if(identicalClass(otherCourse) && !otherCourse.identiClassWithLab(labWeekDay))
        {
            if(otherCourse.getTimeFrom() > timeTo || timeFrom > otherCourse.getTimeTo())
            {
                result = true;
            }
        }
        else if(otherCourse.identiClassWithLab(labWeekDay) && !identicalClass(otherCourse))
        {
            if(otherCourse.getTimeFrom() > labTimeTo || labTimeFrom > otherCourse.getTimeTo())
            {
                result = true;
            }
        }
        else
        {
            result = true;
        }

        return result;
    }

    public String printCourse()
    {
        return (courseName + "     " + section + "     "  +
                timeFrom + "     " + timeTo + "     " + weekDay + "     " +
                labTimeFrom + "     " + labTimeTo + "     " + labWeekDay);
    }

}