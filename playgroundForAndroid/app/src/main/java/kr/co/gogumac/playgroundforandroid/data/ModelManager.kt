//package kr.co.gogumac.playgroundforandroid.data
//
//import com.google.android.gms.tasks.Task
//import com.google.android.gms.tasks.Tasks
//import com.google.mlkit.common.model.DownloadConditions
//import com.google.mlkit.common.model.RemoteModelManager
//import com.google.mlkit.vision.digitalink.DigitalInkRecognition
//import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModel
//import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModelIdentifier
//import com.google.mlkit.vision.digitalink.DigitalInkRecognizer
//import com.google.mlkit.vision.digitalink.DigitalInkRecognizerOptions
//
//class ModelManager {
//
//    var model:DigitalInkRecognitionModel?=null
//    var recognizer: DigitalInkRecognizer?=null
//    val remoteModelManager=RemoteModelManager.getInstance()
//
//    fun setModel(languageTag:String):String{
//        model=null
//        recognizer?.close()
//        recognizer=null
//
//        var modelIdentifier:DigitalInkRecognitionModelIdentifier?=null
//        val setModelIdentifier=kotlin.runCatching {
//            modelIdentifier=DigitalInkRecognitionModelIdentifier.fromLanguageTag(languageTag)
//        }
//
//        if(setModelIdentifier.isFailure)return ""
//        if(modelIdentifier==null){
//            return "No model for language: $languageTag"
//        }
//
//        model=DigitalInkRecognitionModel.builder(modelIdentifier!!).build()
//        recognizer= DigitalInkRecognition.getClient(DigitalInkRecognizerOptions.builder(model).build())
//        return "Model set for language: $languageTag"
//    }
//
//    fun downloadModel(): Task<String> {
//        if(model==null){
//            return Tasks.forResult("Model not selected.")
//        }
//
//        return remoteModelManager
//            .download(model!!,DownloadConditions.Builder().build())
//            .onSuccessTask{
//                Tasks.forResult("Download model successfully")
//            }
//            .addOnFailureListener {  }
//    }
//
//
//
//    fun checkModelDownloaded()=remoteModelManager.isModelDownloaded(model)
//
//
//}