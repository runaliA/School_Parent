package com.infitronics.www.School_Parent.models;

import java.util.List;

/**
 * Created by shashank on 28/4/17.
 */

public class Get_LeaveList {
    private List<Data> Data;

    private String Status;

    private String Message;

    public List<Get_LeaveList.Data> getData ()
    {
        return Data;
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
        private String StudentId;

        private String EndDate;

        private String StartDate;

        private String AdmissionNo;

        private String RID;

        private String SubmittedDate;

        private String StudentName;

        private String lv_Description;

        public String getStudentId ()
        {
            return StudentId;
        }

        public void setStudentId (String StudentId)
        {
            this.StudentId = StudentId;
        }

        public String getEndDate ()
        {
            return EndDate;
        }

        public void setEndDate (String EndDate)
        {
            this.EndDate = EndDate;
        }

        public String getStartDate ()
        {
            return StartDate;
        }

        public void setStartDate (String StartDate)
        {
            this.StartDate = StartDate;
        }

        public String getAdmissionNo ()
        {
            return AdmissionNo;
        }

        public void setAdmissionNo (String AdmissionNo)
        {
            this.AdmissionNo = AdmissionNo;
        }

        public String getRID ()
        {
            return RID;
        }

        public void setRID (String RID)
        {
            this.RID = RID;
        }

        public String getSubmittedDate ()
        {
            return SubmittedDate;
        }

        public void setSubmittedDate (String SubmittedDate)
        {
            this.SubmittedDate = SubmittedDate;
        }

        public String getStudentName ()
        {
            return StudentName;
        }

        public void setStudentName (String StudentName)
        {
            this.StudentName = StudentName;
        }

        public String getLv_Description ()
        {
            return lv_Description;
        }

        public void setLv_Description (String lv_Description)
        {
            this.lv_Description = lv_Description;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [StudentId = "+StudentId+", EndDate = "+EndDate+", StartDate = "+StartDate+", AdmissionNo = "+AdmissionNo+", RID = "+RID+", SubmittedDate = "+SubmittedDate+", StudentName = "+StudentName+", lv_Description = "+lv_Description+"]";
        }
    }

}
