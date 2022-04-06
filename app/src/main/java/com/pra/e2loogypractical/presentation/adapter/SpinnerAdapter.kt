package com.pra.e2loogypractical.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.pra.e2loogypractical.R
import com.pra.e2loogypractical.data.model.response.SubCategoryModel

class SpinnerAdapter(
    theContext: Context, objects: List<SubCategoryModel>,
    theLayoutResId: Int
) : ArrayAdapter<SubCategoryModel?>(theContext, theLayoutResId, objects) {
    private val inflater: LayoutInflater = theContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val objectList: List<SubCategoryModel> = objects

    override fun getCount(): Int {
        // don't display last item. It is used as hint.
        val count = super.getCount()
        return count
       // return if (count > 0) count - 1 else count
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, R.layout.row_spinner_item)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent, R.layout.row_spinner_display_item)
    }

    // This funtion called for each row ( Called data.size() times )
    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?, layoutId: Int): View {
        /********** Inflate spinner_rows.xml file for each row ( Defined below )  */
        val row: View = inflater.inflate(layoutId, parent, false)
        /***** Get each Model object from Arraylist  */
        val label: TextView = row.findViewById<View>(R.id.item_name) as TextView
        label.text = objectList[position].subCategoryName
        return row
    }

}