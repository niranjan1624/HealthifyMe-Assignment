package android.healthifyme.com.api.response;

import android.healthifyme.com.core.models.DaySlot;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Niranjan on 4/12/2016.
 */
public class ApiResponse {

    @SerializedName("slots")
    public Map<String, DaySlot> slots;

    public ArrayList<DaySlot> getSlots() {
        ArrayList<DaySlot> daySlots = new ArrayList<>();
        Iterator iterator = slots.keySet().iterator();
        while (iterator.hasNext()) {
            DaySlot daySlot = (DaySlot) slots.get((String)iterator.next());
            daySlot.addAll();
            daySlots.add(daySlot);
        }
        return daySlots;
    }
}
