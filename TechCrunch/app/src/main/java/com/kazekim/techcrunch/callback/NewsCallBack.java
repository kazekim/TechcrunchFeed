package com.kazekim.techcrunch.callback;

import com.kazekim.techcrunch.model.NewsCollectionDao;
import com.kazekim.techcrunch.model.NewsDao;
import com.kazekim.techcrunch.model.ResultDao;
import com.kazekim.techcrunch.template.callback.JHCallBack;
import com.kazekim.techcrunch.utils.JHLog;
import com.kazekim.techcrunch.utils.JHSettings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Jirawat Harnsiriwatanakit (Kim) on 4/7/2017 AD.
 * @contact jirawat.h@kazekim.com
 */

public class NewsCallBack extends JHCallBack<ResultDao> {
    public enum Mode {
        List("create_area");

        private final String actionString;

        Mode(String statusString)
        {
            this.actionString = statusString;
        }

        public String toString()
        {
            return actionString;
        }
    }


    private NewsListCallBackListener newsListCallBackListener;
    private Mode mode;

    /**
     * Listener
     */

    public interface NewsListCallBackListener
    {
        void onNewsListLoadSuccess(NewsCallBack.Mode mode, List<NewsDao> daoList);
        void onNewsListLoadFail(NewsCallBack.Mode mode, String message);
        void onConnectionFail(NewsCallBack.Mode callBack, String message);
    }

    /**
     * Function
     */

    public NewsCallBack() {

    }

    public NewsCallBack(Mode mode) {
        this.mode = mode;
    }

    @Override
    public void onSuccess(Response response, ResultDao resultDao) {
        if (response.isSuccessful()) {
            switch (mode){
                case List:
                    handleNewsList(resultDao);
                    break;
            }
        } else {

        }
    }

    @Override
    public void onFailure(Throwable t) {
        switch (mode) {
            case List:
                if (newsListCallBackListener != null)
                    newsListCallBackListener.onNewsListLoadFail(mode, null);
                break;
        }
    }

    /**
     * Action
     */

    public void loadNewsList(NewsListCallBackListener listener)
    {
        this.mode = Mode.List;
        this.newsListCallBackListener = listener;

        HttpManager httpManager = new HttpManager(new JHDeserializer<NewsCollectionDao>(NewsCollectionDao.class));
        Call<ResultDao> call = httpManager.getService()
                .loadNewsList("techcrunch","latest", JHSettings.apiKey);
        call.enqueue(this);
    }

    /**
     * Result Handler
     **/

    private void handleNewsList(ResultDao<NewsCollectionDao> dao)
    {
        if(dao.getStatus() == "ok") {
            if (newsListCallBackListener != null)
                newsListCallBackListener.onNewsListLoadSuccess(mode, dao.getData().getDataList());
        }else {
            JHLog.logParser(this.getClass(), "error");
        }
    }
}
