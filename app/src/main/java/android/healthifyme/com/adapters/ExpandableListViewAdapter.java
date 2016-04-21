package android.healthifyme.com.adapters;

import android.graphics.Color;
import android.healthifyme.com.R;
import android.healthifyme.com.core.models.ListSlotObj;
import android.healthifyme.com.core.models.SlotObj;
import android.healthifyme.com.core.utils.Utils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Niranjan on 4/13/2016.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    List<ListSlotObj> listSlotObjs;
    String[] dayTimings = new String[]{"Morning", "Afternoon", "Evening"};

    public ExpandableListViewAdapter(List<ListSlotObj> listSlotObjs) {
        this.listSlotObjs = listSlotObjs;
    }

    public void updateList(List<ListSlotObj> listSlotObjs) {
        this.listSlotObjs = listSlotObjs;
        notifyDataSetChanged();
    }
    @Override
    public int getGroupCount() {
        return listSlotObjs.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listSlotObjs.get(groupPosition).listSlot.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listSlotObjs.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ListSlotObj listSlotObj = (ListSlotObj) getGroup(groupPosition);
        return listSlotObj.listSlot.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .slot_parent, parent, false);
        }

        TextView slotTiming = (TextView) convertView.findViewById(R.id.slot_timing);
        TextView slotsAvailable = (TextView) convertView.findViewById(R.id.slots_available);
        ImageView indicator = (ImageView) convertView.findViewById(R.id.indicator);

        slotTiming.setText(dayTimings[groupPosition]);
        slotsAvailable.setText(getChildrenCount(groupPosition) + " slots available");

        if(isExpanded)
            rotateImage(indicator);
        else
            indicator.setImageResource(R.mipmap.ic_up_down);
        return convertView;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
            super.onGroupCollapsed(groupPosition);
    }

    private void rotateImage(ImageView indicator) {
        indicator.setRotation(180);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                    .slot_child, parent, false);
        }

        TextView slot = (TextView) convertView.findViewById(R.id.slot);
        SlotObj slotObj = (SlotObj) getChild(groupPosition, childPosition);
        if(groupPosition == 0)
            slot.setText(new Utils().getDurationString(slotObj.startTime, slotObj.endTime, "AM"));
        else
            slot.setText(new Utils().getDurationString(slotObj.startTime, slotObj.endTime, "PM"));
        if(slotObj.isBooked || slotObj.isExpired)
            slot.setBackgroundColor(Color.parseColor("#cccccc"));
        else
            slot.setBackgroundColor(Color.parseColor("#ffffff"));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
