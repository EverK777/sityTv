package com.jobsity.sitytv.core.data.external.data_source

import com.jobsity.sitytv.core.domain.models.EpisodeDetailResponse
import com.jobsity.sitytv.core.domain.models.SearchResultResponse
import com.jobsity.sitytv.core.domain.models.SeasonsResponse
import com.jobsity.sitytv.core.domain.models.ShowResponse
import com.jobsity.sitytv.core.helpers.ApiResultHandle
import com.jobsity.sitytv.core.helpers.SafeApiRequest
import kotlinx.coroutines.CoroutineDispatcher

class TvMazeDataSource constructor(
    private val tvMazeApi: TvMazeApi,
    private val safeApiRequest: SafeApiRequest,
    private val coroutineDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    override suspend fun requestShows(page: Int): ApiResultHandle<ShowResponse> {
        return safeApiRequest.safeApiRequest(coroutineDispatcher) { tvMazeApi.requestCurrentSeriesList(page) }
    }

    override suspend fun requestSeasons(id: Int): ApiResultHandle<SeasonsResponse> {
        return safeApiRequest.safeApiRequest(coroutineDispatcher) { tvMazeApi.requestSeasons(id) }
    }

    override suspend fun requestEpisodeDetail(showId: Int, season: Int, episodeNumber: Int): ApiResultHandle<EpisodeDetailResponse> {
        return safeApiRequest.safeApiRequest(coroutineDispatcher) { tvMazeApi.getEpisodeDetail(id = showId, season = season, number = episodeNumber) }
    }

    override suspend fun searchShows(query: String): ApiResultHandle<SearchResultResponse> {
        return safeApiRequest.safeApiRequest(coroutineDispatcher) { tvMazeApi.searchShow(query) }
    }
}
