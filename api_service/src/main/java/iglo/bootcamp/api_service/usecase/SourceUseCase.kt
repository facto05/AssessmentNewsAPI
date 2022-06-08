package iglo.bootcamp.api_service.usecase

import iglo.bootcamp.api_service.service.SourceService
import iglo.bootcamp.common.entity.base_response.AppResponse
import iglo.bootcamp.common.entity.source.Source
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class SourceUseCase(private val sourceService: SourceService) {
    operator fun invoke(category : String) = callbackFlow {
        launch {
            send(AppResponse.loading())
            val response = sourceService.getSource(category)
            if(response.isSuccessful){
                response.body()?.sources?.let {
                    send(AppResponse.success(it))
                }
            }
            else{
                send(AppResponse.error<List<Source>>(Exception("Invalid Data"), response.errorBody()))
            }
            close()
        }
        awaitClose()
    }
}