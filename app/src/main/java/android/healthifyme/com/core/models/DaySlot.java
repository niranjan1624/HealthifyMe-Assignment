package android.healthifyme.com.core.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niranjan on 4/12/2016.
 */
public class DaySlot implements Serializable{
    @SerializedName("morning")
    public List<SlotObj> morningSlots;

    @SerializedName("afternoon")
    public List<SlotObj> afternoonSlots;

    @SerializedName("evening")
    public List<SlotObj> eveningSlots;

    public ArrayList<List<SlotObj>> slots = new ArrayList<>();

    public void addAll() {
        slots.add(0, morningSlots);
        slots.add(1, afternoonSlots);
        slots.add(2, eveningSlots);
    }
}
