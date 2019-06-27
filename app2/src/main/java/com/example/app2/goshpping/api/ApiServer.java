package com.example.app2.goshpping.api;



import com.example.app2.goshpping.bean.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer {
    String url="http://www.qubaobei.com/ios/cf/";
    @GET("dish_list.php?stage_id=1&limit=20")
    Observable<User> getRxJava(@Query("page") int page);
}
