package com.hy.hyspirit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.testasr.R;
import com.xuexiang.xui.widget.layout.ExpandableLayout;

import butterknife.BindView;
import butterknife.OnClick;


public class ExpandableHorizontalFragment extends Fragment {

    ExpandableLayout expandableLayout;
    AppCompatImageView expandButton;
    int i=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expandable_horizontal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expandableLayout=getActivity().findViewById(R.id.expandable_layout);
        expandButton=getActivity().findViewById(R.id.expand_button);
        expandableLayout.setOnExpansionChangedListener((expansion, state) -> {
            if (expandButton != null) {
                expandButton.setRotation(expansion * 180);

            }
        });
        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i>0) {

                    expandableLayout.toggle();
                    expandButton.setSelected(true);
                    i*=-1;
                }else {
                    expandableLayout.toggle();
                    expandButton.setSelected(false);
                    i*=-1;
                }
                Log.i("expandButton","--------"+i);
            }
        });
    }
}