package com.example.appexample

class ArticlesModel(
    var title: String,
    var description: String
) : BaseModel() {
    override fun getLayoutType(): Int {
        return R.layout.card_article_blue
    }

    override fun toString(): String {
        return "ArticlesModel(title='$title', description='$description')"
    }

}

class AdsModel(var url: String) : BaseModel() {
    override fun getLayoutType(): Int {
        return R.layout.card_ads
    }

    override fun toString(): String {
        return "AdsModel(url='$url')"
    }
}

class LoadingModel : BaseModel() {
    override fun getLayoutType(): Int {
        return R.layout.layout_progress_bar
    }

    companion object{
        fun addProgress(): MutableList<BaseModel> {
            val items = mutableListOf<BaseModel>()
            items.add(LoadingModel())
            return items
        }
    }
}

abstract class BaseModel {
    abstract fun getLayoutType(): Int


}

