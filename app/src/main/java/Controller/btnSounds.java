package Controller;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.iwb303.R;

 public class btnSounds {
    public static void SetSounds(Context context,Boolean IsMute,int raw)
    {
        if (!IsMute) {
            MediaPlayer md = MediaPlayer.create(context, raw);
            md.start();
        }
    }
}
