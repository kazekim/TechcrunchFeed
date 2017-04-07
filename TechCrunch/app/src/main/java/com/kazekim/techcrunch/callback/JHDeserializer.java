package com.kazekim.techcrunch.callback;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.kazekim.techcrunch.model.ResultDao;
import com.kazekim.techcrunch.utils.JHLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class JHDeserializer <T> implements JsonDeserializer<ResultDao<T>> {

    protected Type typeOfData;

    public JHDeserializer(Type typeOfData)
    {
        super();
        this.typeOfData = typeOfData;
    }

    @Override
    public ResultDao<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        json = json.getAsJsonObject();
        JHLog.logParser(this.getClass(),"SSSS"+json.toString());

        JsonObject resultObject = json.getAsJsonObject();
        ResultDao<T> resultDao = new ResultDao<>();

        String dataKey = ResultDao.RESULTDAOKEY_DATA;

        if(typeOfData != ResultDao.class && resultObject.has(dataKey)) {
            resultDao.setDataList(daoListFromObject(resultObject, dataKey, typeOfData));
        }

        return resultDao;

    }

    private T daoFromObject(JsonObject object, String key, Type typeOfData)
    {
        T dao = new Gson().fromJson(object.get(key), typeOfData);
        return dao;
    }

    private List<T> daoListFromObject(JsonObject object, String key, Type typeOfData)
    {
//        JHLog.logParser(this.getClass(),object.get(key).toString());
        List<T> daoList = new ArrayList<>();
        for (JsonElement dataElement : object.get(key).getAsJsonArray()) {
            T dataDao = new Gson().fromJson(dataElement, typeOfData);
            daoList.add(dataDao);
        }
        return daoList;
    }

}
