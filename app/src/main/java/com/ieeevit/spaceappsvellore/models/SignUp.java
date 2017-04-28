
package com.ieeevit.spaceappsvellore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SignUp implements Serializable{

    @SerializedName("time_sign_up")
    @Expose
    private String timeSignUp;
    @SerializedName("lunch1")
    @Expose
    private Boolean lunch1;
    @SerializedName("lunch2")
    @Expose
    private Boolean lunch2;
    @SerializedName("dinner")
    @Expose
    private Boolean dinner;
    @SerializedName("admin")
    @Expose
    private Boolean admin;
    @SerializedName("attendance")
    @Expose
    private Boolean attendance;
    @SerializedName("activation_token")
    @Expose
    private String activationToken;
    @SerializedName("component_req")
    @Expose
    private List<Object> componentReq = null;
    @SerializedName("component_given")
    @Expose
    private List<Object> componentGiven = null;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("skill")
    @Expose
    private String skill;
    @SerializedName("college")
    @Expose
    private String college;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("wifi_username")
    @Expose
    private String wifiUsername;
    @SerializedName("wifi_password")
    @Expose
    private String wifiPassword;
    @SerializedName("_id")
    @Expose
    private String id;

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getTimeSignUp() {
        return timeSignUp;
    }

    public void setTimeSignUp(String timeSignUp) {
        this.timeSignUp = timeSignUp;
    }

    public void setLunch1(Boolean lunch1) {
        this.lunch1 = lunch1;
    }

    public Boolean getLunch2() {
        return lunch2;
    }

    public void setLunch2(Boolean lunch2) {
        this.lunch2 = lunch2;
    }

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public List<Object> getComponentReq() {
        return componentReq;
    }

    public void setComponentReq(List<Object> componentReq) {
        this.componentReq = componentReq;
    }

    public List<Object> getComponentGiven() {
        return componentGiven;
    }

    public void setComponentGiven(List<Object> componentGiven) {
        this.componentGiven = componentGiven;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWifiUsername() {
        return wifiUsername;
    }

    public void setWifiUsername(String wifiUsername) {
        this.wifiUsername = wifiUsername;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
