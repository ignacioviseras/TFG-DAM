package com.edix.grupo02_codigo_control_de_acceso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.edix.grupo02_codigo_control_de_acceso.helpers.AppUtils;

@SuppressWarnings("ALL")
public class MenuFragment extends Fragment {
    public MenuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Context context = inflater.getContext();
        String activity_name = getActivity().getClass().getSimpleName();
        boolean isAdmin = AppUtils.isAdmin(context);
        // Obtén las referencias a los botones en el layout del fragmento
        ImageButton eventsBtn = view.findViewById(R.id.events);
        ImageButton editProfileBtn = view.findViewById(R.id.editProfile);
        ImageButton checkQrBtn = view.findViewById(R.id.scanAccess);
        ImageButton accessBtn = view.findViewById(R.id.boughtAccesses);

        if(isAdmin){
            checkQrBtn.setOnClickListener(v -> {
                goToActivity(ScanQrActivity.class);
            });
            accessBtn.setVisibility(View.VISIBLE);
        }else{
            accessBtn.setOnClickListener(v -> {
                goToActivity(MainActivity.class);
            });
            accessBtn.setVisibility(View.VISIBLE);
        }
        editProfileBtn.setOnClickListener(v -> {
            goToActivity(EditProfileActivity.class);
        });

        eventsBtn.setOnClickListener(v -> {
            goToActivity(EventsActivity.class);
        });

        switch (getActivity().getClass().getSimpleName()){
            case "MainActivity":
                activateBtn(accessBtn, true);
                break;
            case "EditProfileActivity":
                activateBtn(editProfileBtn, true);
                break;
            case "EventsActivity":
                activateBtn(eventsBtn, true);
                break;
            case "ScamQrActivity":
                activateBtn(checkQrBtn, true);
                break;
        }

        return view;
    }
    private void deactivateBtns(ImageButton... buttons){
        for (ImageButton btn : buttons) {
            activateBtn(btn, false);
        }
    }
    private void activateBtn(ImageButton btn, Boolean activated){
        int color = R.color.black;
        if(activated){
            color = R.color.light_blue;
        }
        btn.setImageTintList(ContextCompat.getColorStateList(btn.getContext(), color));
    }
    private void goToActivity(Class activity){
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    public static void load(AppCompatActivity activity, int fragmentContainerId) {
        FrameLayout fragmentContainer = activity.findViewById(fragmentContainerId);
        // Crea una instancia del fragmento
        MenuFragment menuFragment = new MenuFragment();
        // Añade el fragmento al contenedor
        activity.getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), menuFragment)
                .commit();
    }
}