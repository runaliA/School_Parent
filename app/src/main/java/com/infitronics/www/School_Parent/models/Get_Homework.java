package com.infitronics.www.School_Parent.models;

import java.util.List;

/**
 * Created by shashank on 27/2/17.
 */

public class Get_Homework {

        private List<Get_Homework.Data> Data;

        private String Status;

        private String Message;

        public List<Get_Homework.Data> getData() {
            return Data;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        @Override
        public String toString() {
            return "ClassPojo [Data = " + Data + ", Status = " + Status + ", Message = " + Message + "]";
        }

    public class Data
    {
        private String ClassId;

        private String Description;

        private String Date;

        private String Class;

        private String RID;

        private String FilePath;

        private String Title;

        public String getClassId ()
        {
            return ClassId;
        }

        public void setClassId (String ClassId)
        {
            this.ClassId = ClassId;
        }

        public String getDescription ()
        {
            return Description;
        }

        public void setDescription (String Description)
        {
            this.Description = Description;
        }

        public String getDate ()
        {
            return Date;
        }

        public void setDate (String Date)
        {
            this.Date = Date;
        }

        public String getClassname ()
        {
            return Class;
        }

        public void setClass (String Class)
        {
            this.Class = Class;
        }

        public String getRID ()
        {
            return RID;
        }

        public void setRID (String RID)
        {
            this.RID = RID;
        }

        public String getFilePath ()
        {
            return FilePath;
        }

        public void setFilePath (String FilePath)
        {
            this.FilePath = FilePath;
        }

        public String getTitle ()
        {
            return Title;
        }

        public void setTitle (String Title)
        {
            this.Title = Title;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ClassId = "+ClassId+", Description = "+Description+", Date = "+Date+", Class = "+Class+", RID = "+RID+", FilePath = "+FilePath+", Title = "+Title+"]";
        }
    }
}