package iglo.bootcamp.assessmentNewsApi.activity.category


fun CategoryActivity.observeLiveData() = with(vm) {
    title = "Category"
    binding.recycler.adapter = adapter
    data.observe(this@observeLiveData) {
        adapter.submitData(it)
    }
}