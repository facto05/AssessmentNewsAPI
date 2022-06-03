package iglo.bootcamp.assessmentNewsApi.activity.list_article

import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.paging.LoadState
import iglo.bootcamp.common.ext.setGone
import iglo.bootcamp.common.ext.setVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ListArticleActivity.initBinding() = with(binding){
    recycler.adapter = adapter.withLoadStateFooter(loadStateAdapter)

    val selectedSourceIds = intent.getStringExtra("EXTRA_SOURCE").orEmpty()
    title = "News"

    textInputSearch.addTextChangedListener {
        vm.searchText = it.toString()
    }

    textInputSearch.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            vm.getArticle(
                source = selectedSourceIds,
                q = vm.searchText
            )
        }
        false
    }

    adapter.addLoadStateListener {
        val list = adapter.snapshot()

        when (if(list.isEmpty()) it.refresh else it.append) {
            is LoadState.Error -> {
                if (list.isEmpty()) {
                    tvNoArticleInfo.setGone()
                    btnRetry.setVisible()
                    recycler.setGone()
                } else {
                    tvNoArticleInfo.setGone()
                    btnRetry.setGone()
                    recycler.setVisible()
                }
            }
            is LoadState.NotLoading -> {
                if (list.isEmpty()) {
                    tvNoArticleInfo.text = "No article found"
                    tvNoArticleInfo.setVisible()
                    btnRetry.setGone()
                    recycler.setGone()
                } else {
                    tvNoArticleInfo.setGone()
                    btnRetry.setGone()
                    recycler.setVisible()
                }
            }
            is LoadState.Loading -> {
                if (list.isEmpty()) {
                    binding.tvNoArticleInfo.setGone()
                    binding.btnRetry.setGone()
                    binding.recycler.setGone()
                } else {
                    tvNoArticleInfo.setGone()
                    btnRetry.setGone()
                    recycler.setVisible()
                }
            }
        }
    }

    vm.getArticle(selectedSourceIds)
    btnRetry.setOnClickListener {
        vm.getArticle(selectedSourceIds)
    }
}

fun ListArticleActivity.observeLiveData() = with(vm){
    data.observe(this@observeLiveData) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
}

fun ListArticleActivity.retry(){
    adapter.retry()
}