package android.healthifyme.com.adapters;

import android.content.Context;
import android.graphics.Color;
import android.healthifyme.com.R;
import android.healthifyme.com.core.models.ListSlotObj;
import android.healthifyme.com.core.models.SlotObj;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

/**
 * Created by Niranjan on 4/13/2016.
 */
public class RecyclerViewAdapter extends ExpandableRecyclerAdapter<RecyclerViewAdapter.SlotParentViewHolder, RecyclerViewAdapter.SlotChildViewHolder> {

    LayoutInflater mInflater;
    Context context;
    String[] dayTimings = new String[]{"Morning", "Afternoon", "Evening"};
    static int[] expandedPositions = new int[] {-1, -1, -1};
    List<? extends ParentListItem> parentItemList;

    public RecyclerViewAdapter(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        this.context = context;
        this.parentItemList = parentItemList;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public SlotParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflater.inflate(R.layout.slot_parent, parentViewGroup, false);
        return new SlotParentViewHolder(view);
    }

    @Override
    public SlotChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflater.inflate(R.layout.slot_child, childViewGroup, false);
        return new SlotChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(SlotParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {

        ListSlotObj slot = (ListSlotObj) parentListItem;
        parentViewHolder.slotTiming.setText(dayTimings[position]);
        parentViewHolder.slotsAvailable.setText(slot.getChildItemList().size()+" slots available");
        if(expandedPositions[position] == 1) {
            //rotateImage(parentViewHolder.indicator);
        }
    }

    private void rotateImage(ImageView indicator) {
        indicator.setRotation(180);
    }

    @Override
    public void onParentListItemExpanded(int position) {
        super.onParentListItemExpanded(position);
        if(expandedPositions[position] == 1)
            expandedPositions[position] = -1;
        else
            expandedPositions[position] = 1;
    }

    @Override
    public void onBindChildViewHolder(SlotChildViewHolder childViewHolder, int position, Object childListItem) {
        SlotObj slotObj = (SlotObj) childListItem;
        Log.d("DEBUG_D", slotObj.startTime);
        if(slotObj.isBooked || slotObj.isExpired)
            childViewHolder.slot.setBackgroundColor(Color.parseColor("#ccc"));
        else
            childViewHolder.slot.setBackgroundColor(Color.parseColor("#fff"));
        childViewHolder.slot.setText(slotObj.endTime);
    }

    public class SlotParentViewHolder extends ParentViewHolder{
        public TextView slotTiming,slotsAvailable;
        ImageView indicator;

        public SlotParentViewHolder(View itemView) {
            super(itemView);

            slotTiming = (TextView) itemView.findViewById(R.id.slot_timing);
            slotsAvailable = (TextView) itemView.findViewById(R.id.slots_available);
            indicator = (ImageView) itemView.findViewById(R.id.indicator);
        }
    }

    public class SlotChildViewHolder extends ChildViewHolder{
        public TextView slot;

        public SlotChildViewHolder(View itemView) {
            super(itemView);

            slot = (TextView) itemView.findViewById(R.id.slot);
        }
    }


}
