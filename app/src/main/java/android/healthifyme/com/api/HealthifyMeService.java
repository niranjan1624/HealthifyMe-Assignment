package android.healthifyme.com.api;

import android.healthifyme.com.api.response.ApiResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by Niranjan on 4/12/2016.
 */
public interface HealthifyMeService {
    @GET("/booking/slots/all")
    Observable<ApiResponse> getSlotDetails(@QueryMap(encodeNames=true) Map<String, String> options);
}
