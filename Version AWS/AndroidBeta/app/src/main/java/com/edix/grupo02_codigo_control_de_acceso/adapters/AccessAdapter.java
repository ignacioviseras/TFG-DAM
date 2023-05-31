package com.edix.grupo02_codigo_control_de_acceso.adapters;

import android.annotation.SuppressLint;
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
import com.edix.grupo02_codigo_control_de_acceso.database.DataBaseUtils;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;
import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;

public class AccessAdapter extends ArrayAdapter<Access> {
    private final List<Access> itemList;


    public AccessAdapter(Context context, List<Access> itemList) {
        super(context, 0, itemList);
        this.itemList = itemList;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.access_row, parent, false);
        }
        Access access = itemList.get(position);
        TextView itemTextView = convertView.findViewById(R.id.accessTitle);
        ImageButton accessQr = convertView.findViewById(R.id.accessQR);
        accessQr.setOnClickListener(v -> showQRCode(parent.getContext(),
                AppUtils.encodeAccessId(access.getEvent_id())));
        Event event = DataBaseUtils.getDBManager(parent.getContext()).eventDao().findById(access.getEvent_id());
        itemTextView.setText(event.getName() + " (x" + access.getAvailables() + ")");
        return convertView;
    }

    public void showQRCode(Context context, String texto) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            int QR_CODE_SIZE = 500;
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
