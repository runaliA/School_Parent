package com.infitronics.www.School_Parent.models;

import java.util.List;

/**
 * Created by shashank on 20/3/17.
 */

public class Get_Remark  {
    private List<Get_Remark.Data> Data;

    private String Status;

    private String Message;

    public List<Get_Remark.Data> getData ()
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
        private String Student;

        private String Description;

        private String Class;

        private String Id;

        private String Title;

        public String getStudent ()
        {
            return Student;
        }

        public void setStudent (String Student)
        {
            this.Student = Student;
        }

        public String getDescription ()
        {
            return Description;
        }

        public void setDescription (String Description)
        {
            this.Description = Description;
        }

        public String getClasses ()
        {
            return Class;
        }

        public void setClass (String Class)
        {
            this.Class = Class;
        }

        public String getId ()
        {
            return Id;
        }

        public void setId (String Id)
        {
            this.Id = Id;
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
            return "ClassPojo [Student = "+Student+", Description = "+Description+", Class = "+Class+", Id = "+Id+", Title = "+Title+"]";
        }
    }

}
