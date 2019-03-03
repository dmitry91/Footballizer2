package com.dmitry.footballizer.models.converter;

import android.arch.persistence.room.TypeConverter;

import com.dmitry.footballizer.models.StandingsList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StandingsConverter {

    @TypeConverter
    public String fromCountryLangList(List<StandingsList.Standings> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StandingsList.Standings>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<StandingsList.Standings> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<StandingsList.Standings>>() {}.getType();
        List<StandingsList.Standings> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }
}
