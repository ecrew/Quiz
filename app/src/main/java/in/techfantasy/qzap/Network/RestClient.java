package in.techfantasy.qzap.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.http.GET;

public class RestClient {
    private static RestApiInterface restApiInterface;
    public static RestApiInterface getClient() {
        if (restApiInterface == null) {
            OkHttpClient.Builder okClient = new OkHttpClient.Builder();
//            okClient.interceptors().add(new Interceptor() {                     //Interceptor to handle requests
//                @Override
//                public Response intercept(Interceptor.Chain chain) throws IOException {
//                    return chain.proceed(chain.request().newBuilder().addHeader("tkn", "cocoalabs").build());
//
//                }
//            });

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();




            OkHttpClient oClient=okClient.build();
            String mBaseUrl = "http://mcgroup.in/skip/QzAp/Admin/";
            Retrofit client =new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .client(oClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            restApiInterface = client.create(RestApiInterface.class);
        }
        return restApiInterface;
    }


    //Rest Api Interface

    public interface RestApiInterface{
        @GET("api.php?op=get")
        Call<JsonElement> getQuestions();
    }
}
