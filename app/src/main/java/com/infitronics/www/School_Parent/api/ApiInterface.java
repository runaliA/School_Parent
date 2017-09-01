package com.infitronics.www.School_Parent.api;

import com.infitronics.www.School_Parent.models.Add_Leave;
import com.infitronics.www.School_Parent.models.GetStudentDetails;
import com.infitronics.www.School_Parent.models.GetTimeTable;
import com.infitronics.www.School_Parent.models.Get_Attendance;
import com.infitronics.www.School_Parent.models.Get_Gallery;
import com.infitronics.www.School_Parent.models.Get_Homework;
import com.infitronics.www.School_Parent.models.Get_LeaveList;
import com.infitronics.www.School_Parent.models.Get_Notice;
import com.infitronics.www.School_Parent.models.Get_Remark;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by shashank on 27/2/17.
 */

public interface ApiInterface {

    @GET("GetNotice")
    Call <Get_Notice> getNotice();

    @FormUrlEncoded
    @POST("GetHomeWorkForStudent")
    Call <Get_Homework> getHomework(@Field("StudentId") int studentid);

    @FormUrlEncoded
    @POST("GetRemark")
    Call <Get_Remark> getRemark(@Field("Type") int type,@Field("Id") int id);

    @GET("Bind_GalleryList")
    Call <Get_Gallery> getGallery();

    @FormUrlEncoded
    @POST("GetLeaveListForTeacher")
    Call <Get_LeaveList> getLeaveList(@Field("TeacherId") int id, @Field("Type") int type);

    @FormUrlEncoded
    @POST("AddLeave")
    Call<Add_Leave> insertLeave(@Field("StartDate") String startDate,@Field("EndDate") String endDate,@Field("Student") int student , @Field("Reason") String reason,@Field("RID") int rid);

    @FormUrlEncoded
    @POST("ValidateUser_Student")
    Call<GetStudentDetails> getStuDetails(@Field("UserId") String userID, @Field("Password") String pass);

    @FormUrlEncoded
    @POST("GetAttendance")
    Call<Get_Attendance> getAttendance(@Field("MonthName") String month, @Field("Student") int Studentid);

    @FormUrlEncoded
    @POST("GetTimeTable")
    Call<GetTimeTable> getTimetable(@Field("ClassId") int classID);

}
