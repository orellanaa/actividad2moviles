package com.example.apppelis;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.example.apppelis.adapter.MovieAdapter;
import com.example.apppelis.model.Movie;
import com.example.apppelis.network.ApiClient;
import com.example.apppelis.network.apiMovie;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
        private List<Movie> movies;
        private RecyclerView recyclerView;
        private MovieAdapter movieAdapter;
        @Override
        protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        showMovies();
        }

        public void showMovies(){
                Call<List<Movie>> call= ApiClient.getClient().create(apiMovie.class).getMovies();
                call.enqueue(new Callback<List<Movie>>() {
                        @Override
                        public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                                if (response.isSuccessful()){
                                        movies=response.body();
                                        movieAdapter=new MovieAdapter(movies,getApplicationContext());
                                        recyclerView.setAdapter(movieAdapter);
                                }
                        }

                        @Override
                        public void onFailure(Call<List<Movie>> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();

                        }
                });
        }
}
