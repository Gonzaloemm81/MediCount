package com.example.cards1;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {

        String channelId = context.getString(R.string.basic_channel_id);
        NotificationCompat.Builder builder = createNotification(context, channelId);
        int notificationId = 1;
        NotificationManagerCompat.from(context).notify(notificationId, builder.build());
    }

    private NotificationCompat.Builder createNotification(Context context, String channelId) {
        // Obtiene el ícono grande (debe estar en res/drawable)
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.pngwing);

        // Crea la notificación con el ícono grande
        return new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.medicount)
                .setLargeIcon(largeIcon)  // Establece el ícono grande aquí
                .setContentTitle("Recordatorio")
                .setContentText("¡No olvides tomar tu medicamento!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
