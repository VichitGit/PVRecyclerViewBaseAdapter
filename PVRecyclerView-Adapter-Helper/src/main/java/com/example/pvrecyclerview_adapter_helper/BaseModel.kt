package com.example.pvrecyclerview_adapter_helper

abstract class BaseModel {
    abstract fun getLayoutType(): Int
}

class LoadingModel : BaseModel() {
    override fun getLayoutType(): Int {
        return R.layout.layout_progress_bar
    }

    companion object {
        fun addProgress(): MutableList<BaseModel> {
            val items = mutableListOf<BaseModel>()
            items.add(LoadingModel())
            return items
        }
    }
}