package com.infitronics.www.School_Parent.models;

import java.util.List;

/**
 * Created by shashank on 27/2/17.
 */

public class Get_Gallery {

    private List<Get_Gallery.Data> Data;

    private String Status;

    private String Message;

    public List<Get_Gallery.Data> getData ()
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
        private String Description;

        private String MediaType;

        private List<Data.LstGallery> lstGallery;

        private String Title;

        public String getDescription ()
        {
            return Description;
        }

        public void setDescription (String Description)
        {
            this.Description = Description;
        }

        public String getMediaType ()
        {
            return MediaType;
        }

        public void setMediaType (String MediaType)
        {
            this.MediaType = MediaType;
        }

        public List<LstGallery> getLstGallery ()
        {
            return lstGallery;
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
            return "ClassPojo [Description = "+Description+", MediaType = "+MediaType+", lstGallery = "+lstGallery+", Title = "+Title+"]";
        }

        public class LstGallery
        {
            private String ImageUrl;

            public String getImageUrl ()
            {
                return ImageUrl;
            }

            public void setImageUrl (String ImageUrl)
            {
                this.ImageUrl = ImageUrl;
            }

            @Override
            public String toString()
            {
                return "ClassPojo [ImageUrl = "+ImageUrl+"]";
            }
        }
    }
}
