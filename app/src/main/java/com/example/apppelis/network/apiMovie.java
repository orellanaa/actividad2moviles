package com.example.apppelis.network;


import com.example.apppelis.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiMovie {

   @GET("movies/list.php")
   Call<List<Movie>> getMovies();
}
