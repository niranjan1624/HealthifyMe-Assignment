package android.healthifyme.com.api;

import android.healthifyme.com.AppConstants;
import android.healthifyme.com.BuildConfig;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

/**
 * Created by Niranjan on 4/12/2016.
 */
public class HealthifyMeApi {
    private static final String SERVER_URL = "http://108.healthifyme.com/api/v1";

    private static HealthifyMeApi sInstance;
    private HealthifyMeService mService;

    /**
     * Singleton "constructor"
     *
     * @return Returns the singleton sInstance
     */
    public static HealthifyMeService getService() {
        if (sInstance == null) {
            sInstance = new HealthifyMeApi();
        }

        return sInstance.mService;
    }

    /**
     * Private constructor
     */
    private HealthifyMeApi() {
        //SERVER_URL = "https://server.easycommute.co/sts";
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(SERVER_URL);

        // add full request/response logs if we are a debug build
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL).setLog(
                    new AndroidLog("RetrofitApi"));
        }

        mService = builder.build().create(HealthifyMeService.class);
    }


    /**
     * A request interceptor used to modify all requests sent through this service. Currently,
     * this is responsible for adding a User-Agent and X-Authorization header to  the request.
     */
    private RequestInterceptor requestInterceptor = new RequestInterceptor() {

        @Override
        public void intercept(RequestInterceptor.RequestFacade request) {
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            request.addQueryParam("api_key", AppConstants.API_KEY);
        }
    };

}

