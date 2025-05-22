package com.ioscan.bottom_sheet_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragmentExample extends BottomSheetDialogFragment
{
    private ImageView closeImage;
    private OnClickListener listener;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Context context;

    public BottomSheetFragmentExample(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        closeImage = view.findViewById(R.id.bottomSheetClose);
        recyclerView = view.findViewById(R.id.bottomSheetRecycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        userAdapter = new UserAdapter(context, User.getUserList());
        recyclerView.setAdapter(userAdapter);

        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
    }

    public interface OnClickListener
    {
        void onClick();
    }

    public void setOnItemClickListener(OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.customBottomSheetDialogTheme);
    }
}
