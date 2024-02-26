package mvvm.app.dragerhill.network

import mvvm.app.dragerhill.model.post
import javax.inject.Inject

// NKHL_4
//implementation declare in  inject in dragger hill  -> Application class @HilltAndroid

class ApiInterfaceImp @Inject
constructor(private val api:ApiInterface){
    suspend fun getPost():List<post> =api.getPost()
}