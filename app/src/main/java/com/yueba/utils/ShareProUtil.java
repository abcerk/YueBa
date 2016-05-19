package com.yueba.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chris
 */
public class ShareProUtil {

    private static ShareProUtil instance;
    private String preferences_name = "settings";
    private Context context;
    private SharedPreferences settings ;

    private ShareProUtil(Context context ){
        this.context = context;
        settings  = context.getSharedPreferences( preferences_name , 0 );
    }

    public static ShareProUtil getInstance( Context context ){
        if ( null == instance ){
            instance = new ShareProUtil( context );
        }
        return instance;
    }

    public String getStringValue( String key, String defult ){
        return settings.getString( key , defult );
    }

    public String getStringValue( String key ){
        return getStringValue( key, null );
    }

    public int getIntValue( String key, int defult ){
        int value = Integer.parseInt( settings.getString( key, String.valueOf( defult ) ) );
        return value;
    }

    public int getIntValue( String key ){
        int value = getIntValue( key, 0 );
        return value;
    }

    public float getFloatValue( String key, float defult ){
        float value = Float.parseFloat( settings.getString( key, String.valueOf( defult ) ) );
        return value;
    }

    public float getFloatValue( String key ){
        float value = getFloatValue( key, 0 );
        return value;
    }

    public double getDoubleValue( String key, double defult ){
        double value = Double.parseDouble( settings.getString( key, String.valueOf( defult ) ) );
        return value;
    }

    public double getDoubleValue( String key ){
        double value = getDoubleValue( key, 0 );
        return value;
    }

    public long getLongValue( String key, long defult ){
        long value = Long.parseLong( settings.getString( key, String.valueOf( defult ) ) );
        return value;
    }

    public long getLongValue( String key ){
        long value = getLongValue( key, 0 );
        return value;
    }

    public boolean getBooleanValue( String key, boolean defult ){
        boolean value = Boolean.parseBoolean( settings.getString( key, String.valueOf( defult ) ) );
        return value;
    }

    public boolean getBooleanValue( String key ){
        boolean value = getBooleanValue( key, false );
        return value;
    }

    public boolean putValue( String key, String value ){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString( key, value );
        boolean success = editor.commit();
        return success;
    }

    public boolean putValue( String key, int value ){
        boolean success = putValue(key, String.valueOf(value));
        return success;
    }

    public boolean putValue( String key, long value ){
        boolean success = putValue(key, String.valueOf(value));
        return success;
    }

    public boolean putValue( String key, float value ){
        boolean success = putValue(key, String.valueOf(value));
        return success;
    }

    public boolean putValue( String key, double value ){
        boolean success = putValue(key, String.valueOf(value));
        return success;
    }

    public boolean putValue( String key, boolean value ){
        boolean success = putValue(key, String.valueOf(value));
        return success;
    }

}
