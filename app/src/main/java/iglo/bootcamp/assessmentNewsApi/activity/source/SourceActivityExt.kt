package iglo.bootcamp.assessmentNewsApi.activity.source

import androidx.core.widget.addTextChangedListener
import iglo.bootcamp.common.entity.base_response.AppResponseError
import iglo.bootcamp.common.entity.base_response.AppResponseLoading
import iglo.bootcamp.common.entity.base_response.AppResponseSuccess
import iglo.bootcamp.common.ext.setGone
import iglo.bootcamp.common.ext.setVisible

fun SourceActivity.observeLiveData() = with(vm) {
    data.observe(this@observeLiveData) {
        when(it){
            is AppResponseLoading -> {
                binding.btnRetry.setGone()
                binding.progressBar.setVisible()
            }
            is AppResponseSuccess -> {
                binding.btnRetry.setGone()
                binding.progressBar.setGone()
                it.data?.let { list ->
                    adapter.data.submitList(list)
                }
            }
            is AppResponseError -> {
                binding.progressBar.setGone()
                binding.btnRetry.setVisible()
            }
        }
    }
}

fun SourceActivity.initBinding(category : String) = with(binding){
    title = "Source News"
    binding.recycler.adapter = adapter
    vm?.getSource(category)
    binding.inputText.addTextChangedListener {
        vm?.searchText = it.toString()
        vm?.filterSource()
        adapter.data.submitList(vm?.listSearch)
    }
    binding.btnRetry.setOnClickListener {
        vm?.getSource(category)
    }
}