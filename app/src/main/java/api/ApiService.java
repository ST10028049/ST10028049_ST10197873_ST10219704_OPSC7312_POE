package api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/user/signup")
    Call<SignUpResponse> signUpUser(@Body SignUpRequest signUpRequest);

    @Headers("Content-Type: application/json")
    @POST("/user/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("/exercise/addExercise")
    Call<ExerciseResponse> addExercise(@Body ExerciseRequest exerciseRequest);

    @Headers("Content-Type: application/json")
    @POST("/meals/logMeal")
    Call<MealResponse> logMeal(@Body MealRequest mealRequest);
}
