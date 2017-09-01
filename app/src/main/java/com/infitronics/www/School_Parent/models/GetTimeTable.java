package com.infitronics.www.School_Parent.models;

/**
 * Created by Priyanka on 6/23/2017.
 */
public class GetTimeTable
{
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

    public class Data {
        private String Day;

        private Timetable[] Timetable;

        public String getDay() {
            return Day;
        }

        public void setDay(String Day) {
            this.Day = Day;
        }

        public Timetable[] getTimetable() {
            return Timetable;
        }

        public void setTimetable(Timetable[] Timetable) {
            this.Timetable = Timetable;
        }

        @Override
        public String toString() {
            return "ClassPojo [Day = " + Day + ", Timetable = " + Timetable + "]";
        }


        public class Timetable {
            private String STime;

            private String Subject;

            private String ETime;

            public String getSTime() {
                return STime;
            }

            public void setSTime(String STime) {
                this.STime = STime;
            }

            public String getSubject() {
                return Subject;
            }

            public void setSubject(String Subject) {
                this.Subject = Subject;
            }

            public String getETime() {
                return ETime;
            }

            public void setETime(String ETime) {
                this.ETime = ETime;
            }

            @Override
            public String toString() {
                return "ClassPojo [STime = " + STime + ", Subject = " + Subject + ", ETime = " + ETime + "]";
            }
        }
    }

}

