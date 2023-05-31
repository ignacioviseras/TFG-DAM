package com.edix.grupo02_codigo_control_de_acceso.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.edix.grupo02_codigo_control_de_acceso.R;
import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccessAdapter extends ArrayAdapter<Access> {
    private List<Access> itemList;


    public AccessAdapter(Context context, List<Access> itemList) {
        super(context, 0, itemList);
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.access_row, parent, false);
        }
        Access access = itemList.get(position);
        TextView itemTextView = convertView.findViewById(R.id.accessTitle);
        ImageButton accessQr = convertView.findViewById(R.id.accessQR);
        accessQr.setOnClickListener(v -> {
            showQRCode(parent.getContext(), access.getEvent_id() + "");
        });


        Call<Event> call = ApiAdapter.getApiService().getEvents(access.getEvent_id());
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.isSuccessful()) {
                    Event eventResponse = response.body();
                    itemTextView.setText(eventResponse.getName() + " (x" + access.getAvailables() + ")");
                } else {

                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });

        return convertView;
    }

    private final int QR_CODE_SIZE = 500;

    public void showQRCode(Context context, String texto) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            ImageView imageView = new ImageView(context);
            imageView.setImageBitmap(bitmap);

            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setView(imageView)
                    .setPositiveButton("Aceptar", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
