package eu.corellis.centrale.activitylifecycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.centrale.thedailysorcerer.ListArticlesFragment
import com.centrale.thedailysorcerer.R
import layout.Article


class CustomAdapter(private val dataSet: ArrayList<Article?>, var aListener: OnArticleSelectedListener, val bListener: OnBottomReachedListener) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val TAG = "View Holder"

        val txtTitle: TextView
        val txtAuthor: TextView
        val txtDate: TextView
        //val imgArticle: ImageView

        init {
            txtTitle = v.findViewById(R.id.txtTitle)
            txtAuthor = v.findViewById(R.id.txtAuthor)
            txtDate = v.findViewById(R.id.txtDate)
            //imgArticle = v.findViewById(R.id.imgArticle)
        }

    }



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_article, viewGroup, false)

        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.itemView.setOnClickListener { aListener.onArticleSelected(dataSet[position]!!) }
        viewHolder.txtTitle.text = dataSet[position]?.title
        viewHolder.txtAuthor.text = dataSet[position]?.author
        viewHolder.txtDate.text = dataSet[position]?.date
    }



    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    companion object {
        private lateinit var aListener: OnArticleSelectedListener
        private val TAG = "CustomAdapter"
    }


    public interface OnArticleSelectedListener{
        fun onArticleSelected(article: Article)
    }

    public interface OnBottomReachedListener {
        fun onBottomReached()
    }



}
