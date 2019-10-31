package com.vferreirati.moviescatalog.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vferreirati.moviescatalog.presentation.Movie
import com.vferreirati.moviescatalog.repository.MoviesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {
    private val currentState = MutableLiveData<DetailsState>()
    val state get() = currentState as LiveData<DetailsState>

    fun getRelatedMovies(movieId: Int) {
        viewModelScope.launch {
            currentState.postValue(LoadingRelatedMovies())
            try {
                val movies = moviesRepository.getMoviesRecommendations(movieId)
                currentState.postValue(RelatedMoviesLoded(movies))
            } catch (e: Throwable) {
                currentState.postValue(ErrorLoadingRelatedMovies("Ocorreu um erro ao obter a recomendação de filmes, verifique sua conexão com a internet"))
            }
        }
    }
}

abstract class DetailsState

class LoadingRelatedMovies : DetailsState()

class ErrorLoadingRelatedMovies(
    val errorMessage: String
) : DetailsState()

class RelatedMoviesLoded(
    val relatedMovies: List<Movie>
) : DetailsState()
