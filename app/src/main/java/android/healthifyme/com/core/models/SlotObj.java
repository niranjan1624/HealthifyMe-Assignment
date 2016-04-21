package android.healthifyme.com.core.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niranjan on 4/12/2016.
 */
public class SlotObj implements Serializable {

    @SerializedName("end_time")
    public String endTime;

    @SerializedName("start_time")
    public String startTime;

    @SerializedName("is_booked")
    public boolean isBooked;

    @SerializedName("is_expired")
    public boolean isExpired;

    @SerializedName("slot_id")
    public int slotId;
}
