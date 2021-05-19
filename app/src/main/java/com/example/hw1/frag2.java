package com.example.hw1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frag2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private Button Scan;
    private RecyclerView slist;
    ArrayList<scan> findDevice = new ArrayList<>();
    recycle mAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frag2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag2.
     */
    // TODO: Rename and change types and number of parameters
    public static frag2 newInstance(String param1, String param2) {
        frag2 fragment = new frag2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_frag1, container, false);
        Scan=view.findViewById(R.id.Startscan);
        slist=view.findViewById(R.id.recycler_view);
        mAdapter=new recycle(findDevice);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        slist.setLayoutManager(layoutManager);
        slist.setAdapter(mAdapter);
        return view;
    }
}