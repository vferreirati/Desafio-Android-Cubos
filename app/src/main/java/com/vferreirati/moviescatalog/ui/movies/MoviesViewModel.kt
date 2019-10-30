package com.vferreirati.moviescatalog.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vferreirati.moviescatalog.enums.MovieGenres
import com.vferreirati.moviescatalog.presentation.Movie
import com.vferreirati.moviescatalog.repository.MoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    private var currentPage = 1
    private val loadedMovies = mutableListOf<Movie>()
    private val currentState = MutableLiveData<MoviesListState>()
    val state get() = currentState as LiveData<MoviesListState>

    fun getMovies(genre: MovieGenres) {
        val modelState = currentState.value
        if(modelState is LoadingMovies)
            return

        Log.d("Movies", "Retrieving new movies")

        viewModelScope.launch {
            try {
                currentState.postValue(LoadingMovies(loadedMovies))

                val movies = moviesRepository.queryMovies(currentPage, genre)
                loadedMovies.addAll(movies)

                currentState.postValue(MoviesLoaded(loadedMovies))
                currentPage++

            } catch (t: Throwable) {
                t.printStackTrace()
                currentState.postValue(ErrorLoadingMovies("Ocorreu um erro ao obter os filmes, verifique sua conexão com a internet", loadedMovies))
            }
        }
    }
}

abstract class MoviesListState
class LoadingMovies(val movies: List<Movie>) : MoviesListState()
class ErrorLoadingMovies(
    val errorMessage: String,
    val movies: List<Movie>
) : MoviesListState()
class MoviesLoaded(val movies: List<Movie>) : MoviesListState()