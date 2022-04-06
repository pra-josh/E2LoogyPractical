package com.pra.e2loogypractical.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pra.e2loogypractical.data.model.response.Result
import com.pra.e2loogypractical.databinding.RowMerchantListBinding
import com.pra.myapplication.UI.Listener.OnItemClickListener

class MerchantListAdapter(
    private val context: Context,
    private var resultList: List<Result>
) :
    RecyclerView.Adapter<MerchantListAdapter.MyViewHolder>() {


    fun UpdateMerChant(newCountry: List<Result>) {
        resultList = newCountry as ArrayList<Result>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = RowMerchantListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.tvTitle.text = resultList[position].storeName
        holder.itemBinding.tvDesc.text =
            resultList[position].towerNumber + ". " + resultList[position].unitNumber

        /*Glide.with(context)
            .load(resultList[position].detailImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.itemBinding.progress.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.itemBinding.progress.visibility = View.GONE
                    return false
                }
            })
            .into(holder.itemBinding.ivLogo)*/

    }

    override fun getItemCount(): Int = resultList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    class MyViewHolder(public val itemBinding: RowMerchantListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }
}