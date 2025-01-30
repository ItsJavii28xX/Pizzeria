package Servicios;

import android.content.Context;
import android.content.SharedPreferences;

public class ServicioDatos {

    public static void saveData(Context context, String key, String value) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public static String loadData(Context context, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);

    }

    public static void deleteData(Context context, String key) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();

    }

}


