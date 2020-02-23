package com.gojek_assignment_android.CustomAdatper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gojek_assignment_android.R
import com.gojek_assignment_android.model.TrendingRepositories_model
import com.hendraanggrian.recyclerview.widget.ExpandableItem
import com.hendraanggrian.recyclerview.widget.ExpandableRecyclerView

class TrendingRepo_CustomAdapter(
    layout: LinearLayoutManager,
    private val context: Context,
    private val list: List<TrendingRepositories_model>

) : ExpandableRecyclerView.Adapter<TrendingRepo_CustomAdapter.ViewHolder>(layout) {
    private val price: String? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingRepo_CustomAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repo_row_itm, parent, false)
        return ViewHolder(view)
    }


    @SuppressLint("MissingSuperCall")
    override fun onBindViewHolder(holder: TrendingRepo_CustomAdapter.ViewHolder, position: Int) {

        holder.tv_name.setText(list.get(holder.adapterPosition).name)
        holder.tv_gitname.setText(list.get(holder.adapterPosition).author)
        holder.tv_lng.setText(list.get(holder.adapterPosition).language)
        holder.tv_star.setText(list.get(holder.adapterPosition).stars.toString())
        holder.tv_detail.setText(
            list.get(holder.adapterPosition).description + "( "
                    + list.get(holder.adapterPosition).url + " )"
        )

        holder.tv_github.setText(list.get(holder.adapterPosition).forks.toString())

        holder.item.hide()
        holder.item.setOnClickListener(View.OnClickListener {

            if (holder.item.isOpened != true) {
                holder.item.show()
            } else {

                holder.item.hide()
            }


        })



        Glide.with(context)
            .load(list.get(position).avatar)

            .apply(
                RequestOptions()
                    .circleCrop()
                    .error(R.mipmap.forkblack)
                    .fallback(R.mipmap.forkblack)


            )
            .into(holder.img_profile)


        try {

            var shape = GradientDrawable()
            shape!!.setShape(GradientDrawable.OVAL)
            shape!!.setColor(Color.parseColor(list.get(position).languageColor))

            holder.img_color.setImageDrawable(shape)

        } catch (ex: Exception) {
            System.out.println(ex.stackTrace)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var img_profile: ImageView
        internal var img_color: ImageView

        internal var tv_name: TextView
        internal var tv_gitname: TextView

        internal var tv_lng: TextView
        internal var tv_detail: TextView
        internal var tv_star: TextView
        internal var tv_github: TextView
        internal var item: ExpandableItem

        init {
            item = itemView.findViewById(R.id.row_repo)

            img_profile = itemView.findViewById(R.id.img_profile)
            img_color = itemView.findViewById(R.id.img_color)
            tv_name = itemView.findViewById(R.id.tv_name)
            tv_detail = itemView.findViewById(R.id.tv_detail)
            tv_gitname = itemView.findViewById(R.id.tv_gitname)

            tv_lng = itemView.findViewById(R.id.tv_lng)
            tv_star = itemView.findViewById(R.id.tv_star)
            tv_github = itemView.findViewById(R.id.tv_github)


        }
    }

}