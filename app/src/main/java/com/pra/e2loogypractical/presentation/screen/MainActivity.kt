package com.pra.e2loogypractical.presentation.screen

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.pra.e2loogypractical.R
import com.pra.e2loogypractical.data.model.response.Result
import com.pra.e2loogypractical.data.model.response.SubCategoryModel
import com.pra.e2loogypractical.databinding.ActivityMainBinding
import com.pra.e2loogypractical.presentation.adapter.MerchantListAdapter
import com.pra.e2loogypractical.presentation.adapter.SpinnerAdapter
import com.pra.e2loogypractical.presentation.viewmodel.MainActivityViewModel
import com.pra.myapplication.UI.Util.ViewModelFactory


class MainActivity : BaseActivity() {

    private lateinit var _viewModel: MainActivityViewModel
    private lateinit var _binding: ActivityMainBinding

    private var _adapter: MerchantListAdapter? = null

    private var _subCategoryList: List<SubCategoryModel> = ArrayList()
    private var _resultList: List<Result> = ArrayList()

    /**
     * this method is used to inflating Layout. it will call automatically in OnCreate
     */
    override fun getLayoutResourceId(): View {
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        return _binding.root
    }

    override fun initComponents() {
        // initialize Recyclerview
        _binding.rvMerchantList.setHasFixedSize(true);
        _binding.rvMerchantList.layoutManager = GridLayoutManager(this, 2);

        // initialize viewModel
        val factory = ViewModelFactory(this)
        _viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

    }

    override fun prepareView() {
        observableViewModel()

        _viewModel.fetchMerchant(true)
    }

    override fun setActionListener() {

        // Spinner on Item selected
        _binding.spinCategory.onItemSelectedListener = object : AdapterView.
        OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                _binding.tvSelectedCategory.text = _subCategoryList[position].subCategoryName
                _viewModel.getFilteredData(_subCategoryList[position].subCategoryID) // it will give list as per selection of subcategory
            }
        }
    }

    /***
     * All observer will update from ViewModel
     */
    private fun observableViewModel() {
        _viewModel.subCategoryListUpdate.observe(this, Observer {
            it?.let {
                _subCategoryList = it
                println("Subcategory size ==> " + it.size)
                for (i in _subCategoryList.indices) {
                    println("Subcategory ID=> " + _subCategoryList[i].subCategoryID)
                    println("Subcategory Name=> " + _subCategoryList[i].subCategoryName)
                }
                setAdapterInSpinner()
            }
        })

        _viewModel.resultUpdate.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                _resultList = it
                println("Result size ==> " + it.size)
                setAdapterInRv()
            }
        })

        _viewModel.countryLoadError.observe(this, Observer {
            it?.let {
                if (it) {
                    _binding.tvError.visibility = View.VISIBLE
                    _binding.rvMerchantList.visibility = View.GONE
                } else {
                    _binding.tvError.visibility = View.GONE
                    _binding.rvMerchantList.visibility = View.VISIBLE
                }
            }
        })

        _viewModel.loading.observe(this, Observer {
            it?.let {
                if (it) {
                    _binding.progressBar.visibility = View.VISIBLE
                    //  _binding.rvUser.visibility = View.GONE
                } else {
                    _binding.progressBar.visibility = View.GONE
                    _binding.rvMerchantList.visibility = View.VISIBLE
                }
            }
        })
    }

    /***
     * set Data in Recyclerview
     */
    private fun setAdapterInRv() {
        if (_adapter == null) {
            _adapter = MerchantListAdapter(this, _resultList)
            _binding.rvMerchantList.adapter = _adapter
        } else {
            _adapter?.UpdateMerChant(_resultList)
        }
    }

    /***
     * set data in DropDown
     */
    private fun setAdapterInSpinner() {
        val adapter = SpinnerAdapter(
            this,
            _subCategoryList, R.layout.row_spinner_display_item
        )
        _binding.spinCategory.adapter = adapter
        _binding.spinCategory.setSelection(0)
    }

}