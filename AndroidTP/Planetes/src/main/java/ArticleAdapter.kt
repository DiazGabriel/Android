import adaptateur.Article
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.R

class ArticleAdapter(private val dataset: List<Article>) :
        RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.articleTitle)
            val txtDesc = root.findViewById<TextView>(R.id.articleDescription)
            val img = root.findViewById<ImageView>(R.id.articleImg)
            txtTitle.text = item.title
            txtDesc.text = item.description
            img.setImageResource(item.image)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }
    override fun getItemCount() = dataset.size
}