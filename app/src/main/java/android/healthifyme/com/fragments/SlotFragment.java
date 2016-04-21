package android.healthifyme.com.fragments;

import android.healthifyme.com.R;
import android.healthifyme.com.adapters.ExpandableListViewAdapter;
import android.healthifyme.com.core.models.DaySlot;
import android.healthifyme.com.core.models.ListSlotObj;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

/**
 * Created by Niranjan on 4/12/2016.
 */
public class SlotFragment extends Fragment {
    ExpandableListView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.slot_fragment, container, false);
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (ExpandableListView) getView().findViewById(R.id.recyclerView);


        DaySlot daySlot = (DaySlot) getArguments().getSerializable("Slots");

        buildSlotList(daySlot);

    }

    private void buildSlotList(DaySlot daySlot) {
        ArrayList<ListSlotObj> listSlotObjs = new ArrayList<>();
        for(int i = 0; i < daySlot.slots.size(); i++) {
            ListSlotObj listSlotObj = new ListSlotObj(daySlot.slots.get(i));
            listSlotObjs.add(listSlotObj);
        }
      //  RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), parentListItems);
       // recyclerView.setAdapter(recyclerViewAdapter);
        ExpandableListViewAdapter listViewAdapter = (ExpandableListViewAdapter) recyclerView.getExpandableListAdapter();

        if (listViewAdapter != null && listSlotObjs.size() > 0) {
            listViewAdapter.updateList(listSlotObjs);
        } else if (listSlotObjs.size() > 0) {
            listViewAdapter = new ExpandableListViewAdapter(listSlotObjs);
            recyclerView.setAdapter(listViewAdapter);
        }
    }

}
