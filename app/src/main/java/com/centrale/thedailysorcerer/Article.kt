package layout

import android.os.Parcelable
import com.centrale.thedailysorcerer.Source
import kotlinx.android.parcel.Parcelize

@Parcelize
class Article (var title: String?,
               var author: String?,
               var description: String?,
               var articleurl: String?,
               var imgurl: String?,
               var date: String?,
               var content: String?,
               var source: Source?) : Parcelable {


}