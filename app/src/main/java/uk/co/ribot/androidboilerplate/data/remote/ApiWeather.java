package uk.co.ribot.androidboilerplate.data.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.WetherResponse;

public interface ApiWeather {

    String SERVER_ADDRESS = "http://api.openweathermap.org/data/2.5/";

    String API_KEY = "b421744fc587a1efbe3e104958799d17";
    String CITY = "London";

    @GET("weather")
    Observable<WetherResponse> getData(
            @Query("q") String cityName,
            @Query("appid") String appId
    );

    class Creator{
        public static ApiWeather newApiWeather(){
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiWeather.SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiWeather.class);
        }
    }

}