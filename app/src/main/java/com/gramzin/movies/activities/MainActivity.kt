package com.gramzin.movies.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.gramzin.movies.data.MovieAdapter
import com.gramzin.movies.databinding.ActivityMainBinding
import com.gramzin.movies.model.Movie
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var queue: RequestQueue
    val url = "https://kinopoiskapiunofficial.tech/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS&page=1"
    val key = "431e6c4e-3e5d-4bb1-9e6b-cf9db0530120"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(this)

        val adapter = MovieAdapter()
        binding.moviesRcView.layoutManager = LinearLayoutManager(this)
        binding.moviesRcView.adapter = adapter
        val movies = arrayListOf<Movie>()
        val jsonObjectRequest = object: JsonObjectRequest(Method.GET, url, null,
            Response.Listener { response ->
                val jsonArray = response.getJSONArray("films")
                for (i in 0 until jsonArray.length()){
                    movies.add(getFilm(jsonArray.getJSONObject(i)))
                }
                adapter.addMovies(movies)
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            },
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["accept"] = "application/json"
                params["X-API-KEY"] = key
                return params
            }
        }

        queue.add(jsonObjectRequest)
    }

    fun getFilm(jsonObject: JSONObject): Movie{
        val id = jsonObject.getLong("filmId")
        val name = jsonObject.getString("nameRu")
        val nameEn = jsonObject.getString("nameEn")
        val year = jsonObject.getString("year")
        val rating = jsonObject.getString("rating")
        val poster = jsonObject.getString("posterUrlPreview")
        val filmLength = jsonObject.getString("filmLength")
        val countriesJson = jsonObject.getJSONArray("countries")
        val countries = arrayListOf<String>()
        for (i in 0 until countriesJson.length()) {
            countries.add(countriesJson.getJSONObject(i).getString("country"))
        }
        val genresJson = jsonObject.getJSONArray("genres")
        val genres = arrayListOf<String>()
        for (i in 0 until genresJson.length()) {
            genres.add(genresJson.getJSONObject(i).getString("genre"))
        }
        return Movie(id, name, nameEn, year, filmLength, countries, genres, rating, poster)
    }
}