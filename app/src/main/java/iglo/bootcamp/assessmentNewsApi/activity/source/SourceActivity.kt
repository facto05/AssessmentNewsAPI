package iglo.bootcamp.assessmentNewsApi.activity.source

import android.os.Bundle
import androidx.activity.viewModels
import iglo.bootcamp.assessmentNewsApi.R
import iglo.bootcamp.assessmentNewsApi.databinding.SourceLayoutBinding
import iglo.bootcamp.assessmentNewsApi.view_model.SourceViewModel
import iglo.bootcamp.common.base.BaseActivity

class SourceActivity : BaseActivity<SourceLayoutBinding, SourceViewModel>() {
    override val layoutResourceId: Int = R.layout.source_layout
    override val vm: SourceViewModel by viewModels { vmFactory }
    val adapter = SourceAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val category = intent.getStringExtra("EXTRA_CATEGORY")
        initBinding(category.orEmpty())
        observeLiveData()
    }
}