package com.marcelosmith77.android.baselibrary.api.rest;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Cria service para invocação de api rest
 */
public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    /**
     * Cria service para chamada de api sem autenticação
     *
     * @param serviceClass
     * @param <S>
     * @return service
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass) {
        return createService(baseUrl, serviceClass, null);
    }

    /**
     * Cria service para chamada de api sem autenticação
     *
     * @param serviceClass
     * @param <S>
     * @param authToken    - idToken para autorização da api
     * @return service
     */
    public static <S> S createService(String baseUrl, Class<S> serviceClass, final String authToken) {

        Retrofit retrofit = null;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
            }
        }

        builder.client(httpClient.build());

        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}