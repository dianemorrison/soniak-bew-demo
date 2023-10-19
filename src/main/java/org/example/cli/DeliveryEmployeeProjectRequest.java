package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.joda.time.DateTime;

import java.sql.Date;

public class DeliveryEmployeeProjectRequest {
    private int project_id;
    private int delivery_employee_id;
    private boolean is_Active;
    private Date created_On;
    private Date updated_On;

    @JsonCreator
    public DeliveryEmployeeProjectRequest(
            @JsonProperty("project_id") int project_id,
            @JsonProperty("delivery_employee_id") int delivery_employee_id,
            @JsonProperty("is_Active") boolean is_Active,
            @JsonProperty("created_On") Date created_On,
            @JsonProperty("updated_On") Date updated_On) {
        this.project_id = project_id;
        this.delivery_employee_id = delivery_employee_id;
        this.is_Active = is_Active;
        this.created_On = created_On;
        this.updated_On = updated_On;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getDelivery_employee_id() {
        return delivery_employee_id;
    }

    public void setDelivery_employee_id(int delivery_employee_id) {
        this.delivery_employee_id = delivery_employee_id;
    }

    public boolean isIs_Active() {
        return is_Active;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
    }

    public Date getCreated_On() {
        return created_On;
    }

    public void setCreated_On(Date created_On) {
        this.created_On = created_On;
    }

    public Date getUpdated_On() {
        return updated_On;
    }

    public void setUpdated_On(Date updated_On) {
        this.updated_On = updated_On;
    }
}