package android.healthifyme.com.core.models;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by Niranjan on 4/13/2016.
 */
public class ListSlotObj implements ParentListItem {
    public List<SlotObj> listSlot;

    public ListSlotObj(List<SlotObj> listSlot) {
        setListSlot(listSlot);
    }
    @Override
    public List<SlotObj> getChildItemList() {
        return listSlot;
    }

    public void setListSlot(List<SlotObj> listSlot) {
        this.listSlot = listSlot;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

}
