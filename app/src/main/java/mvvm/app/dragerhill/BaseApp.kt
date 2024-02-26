package mvvm.app.dragerhill

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//NKHL_5
// hold the all dependency in baseApp  declare in manifest

@HiltAndroidApp
class BaseApp:Application() {

}