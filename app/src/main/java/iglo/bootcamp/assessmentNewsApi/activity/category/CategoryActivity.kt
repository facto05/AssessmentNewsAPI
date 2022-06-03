package iglo.bootcamp.assessmentNewsApi.activity.category

import android.os.Bundle
import androidx.activity.viewModels
import iglo.bootcamp.assessmentNewsApi.view_model.CategoryViewModel
import iglo.bootcamp.assessmentNewsApi.R
import iglo.bootcamp.assessmentNewsApi.databinding.CategoryLayoutBinding
import iglo.bootcamp.common.base.BaseActivity

class CategoryActivity : BaseActivity<CategoryLayoutBinding, CategoryViewModel>() {
    override val layoutResourceId: Int = R.layout.category_layout
    override val vm: CategoryViewModel by viewModels { vmFactory }
    val adapter = CategoryAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }
}