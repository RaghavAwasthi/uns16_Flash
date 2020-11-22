package com.unscript.chatbot.utils;

import android.content.Context
import android.content.SharedPreferences

class AppPreference private constructor(activity: Context){

    private val mSharedPreferences : SharedPreferences = activity.getSharedPreferences("APP-PREF", Context.MODE_PRIVATE)
    private val mEditor : SharedPreferences.Editor = mSharedPreferences.edit()
    companion object {
        private var instance: AppPreference? = null
        fun getInstance(arg: Context): AppPreference {
            return when {
                instance != null -> instance!!
                else -> synchronized(this) {
                    if (instance == null) instance = AppPreference(arg)
                    instance!!
                }
            }
        }
    }

    fun putString(key: String, value: String){
        mEditor.putString(key,value)
        mEditor.commit()
    }

    fun getString(key: String, default : String) : String?{
        return mSharedPreferences.getString(key,default)
    }

    fun putObject(key: String, value: String) {
        mEditor.putString(key, value)
        mEditor.commit()
    }


    fun clearPreference() {
        mEditor.clear()
        mEditor.commit()

        mEditor.commit()
    }
}