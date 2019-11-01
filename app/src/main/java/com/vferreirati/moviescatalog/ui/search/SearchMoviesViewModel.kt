package com.vferreirati.moviescatalog.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vferreirati.moviescatalog.presentation.Movie
import com.vferreirati.moviescatalog.repository.MoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchMoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val currentState = MutableLiveData<SearchState>()
    val state get() = currentState as LiveData<SearchState>

    fun searchMovies(query: String) {
        viewModelScope.launch {
            currentState.postValue(SearchingMovies())

            try {
                val result = moviesRepository

            } catch (t: Throwable) {
                currentState.postValue(ErrorSearchingMovies("Ocorreu um erro ao realizar a busca, verifique sua conexão com a internet"))
            }
        }
    }
}

abstract class SearchState

class SearchingMovies : SearchState()

class ErrorSearchingMovies(val errorMessage: String) : SearchState()

class MoviesFound(val movies: List<Movie>) : SearchState()

class NoMoviesFound : SearchState()