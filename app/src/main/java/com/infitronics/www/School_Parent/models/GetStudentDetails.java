package com.infitronics.www.School_Parent.models;

/**
 * Created by DELL on 24-06-2017.
 */

public class GetStudentDetails { private Data[] Data;

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
        private String ClassId;

        private String Name;

        private String AdmissionDate;

        private String Class;

        private String Gender;

        private String Id;

        private String Contact2;

        private String AdmNo;

        private String Contact1;

        public String getClassId ()
        {
            return ClassId;
        }

        public void setClassId (String ClassId)
        {
            this.ClassId = ClassId;
        }

        public String getName ()
        {
            return Name;
        }

        public void setName (String Name)
        {
            this.Name = Name;
        }

        public String getAdmissionDate ()
        {
            return AdmissionDate;
        }

        public void setAdmissionDate (String AdmissionDate)
        {
            this.AdmissionDate = AdmissionDate;
        }

//        public String getClass ()
//        {
//            return Class;
//        }

        public void setClass (String Class)
        {
            this.Class = Class;
        }

        public String getGender ()
        {
            return Gender;
        }

        public void setGender (String Gender)
        {
            this.Gender = Gender;
        }

        public String getId ()
        {
            return Id;
        }

        public void setId (String Id)
        {
            this.Id = Id;
        }

        public String getContact2 ()
        {
            return Contact2;
        }

        public void setContact2 (String Contact2)
        {
            this.Contact2 = Contact2;
        }

        public String getAdmNo ()
        {
            return AdmNo;
        }

        public void setAdmNo (String AdmNo)
        {
            this.AdmNo = AdmNo;
        }

        public String getContact1 ()
        {
            return Contact1;
        }

        public void setContact1 (String Contact1)
        {
            this.Contact1 = Contact1;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ClassId = "+ClassId+", Name = "+Name+", AdmissionDate = "+AdmissionDate+", Class = "+Class+", Gender = "+Gender+", Id = "+Id+", Contact2 = "+Contact2+", AdmNo = "+AdmNo+", Contact1 = "+Contact1+"]";
        }
    }

}
