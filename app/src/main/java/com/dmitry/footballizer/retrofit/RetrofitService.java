package com.dmitry.footballizer.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitService {

    private static API retrofitApi;
    private static String BASE_URL = "https://api.football-data.org/v2/";
    private static String KEY = "c5799160e2184f9994d9fada8e3be404";

    private RetrofitService() {
    }

    public static void clearInstance(){
        retrofitApi = null;
    }

    public static API getInstance() {


//        //setup cache
//        int cacheSize = 10 * 1024 * 1024; // 10 MB
//        Cache cache = new Cache(context.getCacheDir(), cacheSize);
//        Interceptor onlineInterceptor = new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                Request request = original.newBuilder()
//                        .header("Accept", "application/json")
//                        .header("X-Auth-Token", KEY)
//                        .method(original.method(), original.body())
//                        .build();
//                okhttp3.Response response = chain.proceed(request);
//                int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
//                return response.newBuilder()
//                        .header("Cache-Control", "public, max-age=" + maxAge)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        };
//        Interceptor offlineInterceptor= new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                Request request = original.newBuilder()
//                        .header("Accept", "application/json")
//                        .header("X-Auth-Token", KEY)
//                        .method(original.method(), original.body())
//                        .build();
//                if (Utils.isNetworkAvailable(context)) {
//                    int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
//                    request = request.newBuilder()
//                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                            .removeHeader("Pragma")
//                            .build();
//                }
//                return chain.proceed(request);
//            }
//        };
//
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(offlineInterceptor)
//                .addNetworkInterceptor(onlineInterceptor)
//                .cache(cache)
//                    .build();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Accept", "application/json")
                                .header("X-Auth-Token", KEY)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();

        if (retrofitApi == null) {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            retrofitApi = mRetrofit.create(API.class);
        }


        return retrofitApi;
    }


}