// ApiService.java
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
    @POST("/exercises/addExercise")
    Call<ExerciseResponse> addExercise(@Body ExerciseRequest exerciseRequest);

    @Headers("Content-Type: application/json")
    @POST("/exercises/trackExercise")  // New endpoint for trackExercise
    Call<ExerciseResponse> trackExercise(@Body ExerciseRequest exerciseRequest);

    @Headers("Content-Type: application/json")
    @POST("/meals/addMeal")
    Call<MealResponse> addMeal(@Body MealRequest mealRequest);
}
