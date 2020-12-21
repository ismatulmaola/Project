//package id.project.service;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class APIClient {
//
//   public static String BASE_URL ="http://7a35d3faff59.ngrok.io/ ";
//   public static String SECRET_KEY = "_o*a(q)o8-#u=35c$m(_&rpwgb)if6y)cbkk2qs2#jlrsdpox)";
//
//    private static Retrofit retrofit = null;
//    public static Retrofit getClient() {
//
////        Interceptor interceptor = new Interceptor() {
////            @Override
////            public okhttp3.Response intercept(Chain chain) throws IOException {
////                        Request newRequest = chain.request().newBuilder().addHeader("X-Api-Key", APIClient.SECRET_KEY).build();
////                return chain.proceed(newRequest);
////            }
////        };
////        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build(); // pake header postman
//
//        OkHttpClient client = new OkHttpClient.Builder().build(); // tanpa header postman
//        GsonBuilder gb = new GsonBuilder();
//        gb.registerTypeAdapter(String.class, new StringConverter());
//        gb.serializeNulls();
//        Gson gson = gb.create();
//
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(APIClient.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//
//                .client(client)
//                .build();
//        return retrofit;
//    }
//
//}
