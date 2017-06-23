package com.infitronics.www.School_Parent.models;

import java.util.List;

/**
 * Created by shashank on 16/3/17.
 */

public class Get_Notice {

    private List<Data> Data;

    private String Status;

    private String Message;

    public List<Get_Notice.Data> getData ()
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
        private String nt_Description;

        private String nt_Title;

        private String File;

        private String nt_Filepath;

        private String nt_NoticeDate;

        private String nt_Id;

        public String getNt_Description ()
        {
            return nt_Description;
        }

        public void setNt_Description (String nt_Description)
        {
            this.nt_Description = nt_Description;
        }

        public String getNt_Title ()
        {
            return nt_Title;
        }

        public void setNt_Title (String nt_Title)
        {
            this.nt_Title = nt_Title;
        }

        public String getFile ()
        {
            return File;
        }

        public void setFile (String File)
        {
            this.File = File;
        }

        public String getNt_Filepath ()
        {
            return nt_Filepath;
        }

        public void setNt_Filepath (String nt_Filepath)
        {
            this.nt_Filepath = nt_Filepath;
        }

        public String getNt_NoticeDate ()
        {
            return nt_NoticeDate;
        }

        public void setNt_NoticeDate (String nt_NoticeDate)
        {
            this.nt_NoticeDate = nt_NoticeDate;
        }

        public String getNt_Id ()
        {
            return nt_Id;
        }

        public void setNt_Id (String nt_Id)
        {
            this.nt_Id = nt_Id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [nt_Description = "+ nt_Description +", nt_Title = "+nt_Title+", File = "+File+", nt_Filepath = "+nt_Filepath+", nt_NoticeDate = "+nt_NoticeDate+", nt_Id = "+nt_Id+"]";
        }
    }
}
