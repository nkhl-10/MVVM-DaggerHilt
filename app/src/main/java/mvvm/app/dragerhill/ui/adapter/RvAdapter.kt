package mvvm.app.dragerhill.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mvvm.app.dragerhill.databinding.EachRowBinding
import mvvm.app.dragerhill.model.post

//NKHL_13
class RvAdapter
    (private var list:List<post>)
    :RecyclerView.Adapter<RvAdapter.MyAdapter>() {

    private lateinit var  binding:EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.MyAdapter {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MyAdapter(binding.root)
    }

    override fun onBindViewHolder(holder: RvAdapter.MyAdapter, position: Int) {
        binding.title.text = list[position].title
        binding.body.text = list[position].body
    }

    override fun getItemCount(): Int =list.size


    class MyAdapter(itemView: View):RecyclerView.ViewHolder(itemView){}

    fun setData(list:List<post>){
        this.list =list
        notifyDataSetChanged()
    }

}