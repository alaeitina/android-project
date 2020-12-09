package eu.corellis.centrale.activitylifecycle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.centrale.thedailysorcerer.R
import layout.Article


class CustomAdapter (private val dataSet: ArrayList<Article?>) :
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
            v.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
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
        Log.d(TAG, "Element $position set.")

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.txtTitle.text = dataSet[position]?.title
        viewHolder.txtAuthor.text = dataSet[position]?.author
        viewHolder.txtDate.text = dataSet[position]?.date


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    companion object {
        private val TAG = "CustomAdapter"
    }
}
