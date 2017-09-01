package com.infitronics.www.School_Parent.models;

/**
 * Created by Priyanka on 7/3/2017.
 */

public class Get_Attendance {

    private Data[] Data;

    private String Status;

    private String Message;

    public Data[] getData ()
    {
        return Data;
    }

    public void setData (Data[] Data)
    {
        this.Data = Data;
    }

    public String getStatus ()
    {
        return Status;
    }

    public void setStatus (String Status)
    {
        this.Status = Status;
    }

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Data = "+Data+", Status = "+Status+", Message = "+Message+"]";
    }


    public class Data
    {
        private String Date;

        private String Attendance;

        public String getDate ()
        {
            return Date;
        }

        public void setDate (String Date)
        {
            this.Date = Date;
        }

        public String getAttendance ()
        {
            return Attendance;
        }

        public void setAttendance (String Attendance)
        {
            this.Attendance = Attendance;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [Date = "+Date+", Attendance = "+Attendance+"]";
        }
    }
}