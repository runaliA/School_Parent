package com.infitronics.www.School_Parent.models;

/**
 * Created by Shashank on 11-02-2017.
 */

public class Model_Leavenote
{
    private String Current_end_date;

    private String program_artist;

    private String days;

    private String program_description;

    private String program_ID;

    private String program_name;

    private String Current_end_time;

    private String program_image;

    private String Current_start_date;

    private String Current_start_time;

    public String getCurrent_end_date ()
    {
        return Current_end_date;
    }

    public void setCurrent_end_date (String Current_end_date)
    {
        this.Current_end_date = Current_end_date;
    }

    public String getProgram_artist ()
    {
        return program_artist;
    }

    public void setProgram_artist (String program_artist)
    {
        this.program_artist = program_artist;
    }

    public String getDays ()
    {
        return days;
    }

    public void setDays (String days)
    {
        this.days = days;
    }

    public String getProgram_description ()
    {
        return program_description;
    }

    public void setProgram_description (String program_description)
    {
        this.program_description = program_description;
    }

    public String getProgram_ID ()
    {
        return program_ID;
    }

    public void setProgram_ID (String program_ID)
    {
        this.program_ID = program_ID;
    }

    public String getProgram_name ()
    {
        return program_name;
    }

    public void setProgram_name (String program_name)
    {
        this.program_name = program_name;
    }

    public String getCurrent_end_time ()
    {
        return Current_end_time;
    }

    public void setCurrent_end_time (String Current_end_time)
    {
        this.Current_end_time = Current_end_time;
    }

    public String getProgram_image ()
    {
        return program_image;
    }

    public void setProgram_image (String program_image)
    {
        this.program_image = program_image;
    }

    public String getCurrent_start_date ()
    {
        return Current_start_date;
    }

    public void setCurrent_start_date (String Current_start_date)
    {
        this.Current_start_date = Current_start_date;
    }

    public String getCurrent_start_time ()
    {
        return Current_start_time;
    }

    public void setCurrent_start_time (String Current_start_time)
    {
        this.Current_start_time = Current_start_time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Current_end_date = "+Current_end_date+", program_artist = "+program_artist+", days = "+days+", program_description = "+program_description+", program_ID = "+program_ID+", program_name = "+program_name+", Current_end_time = "+Current_end_time+", program_image = "+program_image+", Current_start_date = "+Current_start_date+", Current_start_time = "+Current_start_time+"]";
    }

}


