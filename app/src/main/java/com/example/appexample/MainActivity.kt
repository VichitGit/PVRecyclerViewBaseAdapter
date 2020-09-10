package com.example.appexample

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "pppp"
    private var totalPage: Int = 5
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ArticleAdapter(this, recycler_view)
        adapter.setItems(items().toList())
        recycler_view.adapter = adapter
        adapter.setOnHandlePagination(object : OnHandlePagination {
            override fun onLoanMore(totalPage: Int, currentPage: Int) {
                Log.e(tag, "onLoanMore currentPage = $currentPage")
                adapter.setLoading(true)
                getArticle(currentPage)
            }

            override fun getTotalPage(): Int {
                return totalPage
            }
        })

        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({
                swipe_refresh.isRefreshing = false
            }, 1000)
            adapter.setRefresh()
            adapter.onResultCallback(Result.SUCCESS)
            adapter.setItems(items())
        }
    }

    private fun getArticle(page: Int) {
        Handler().postDelayed({
            Log.e(tag, "getArticle")
            if (swipe_refresh.isRefreshing) {
                Handler().postDelayed({
                    adapter.setRefresh()
                    swipe_refresh.isRefreshing = false
                }, 1000)
            }

            adapter.onResultCallback(Result.SUCCESS)
            adapter.setItems(items())
        }, 1500)
    }

    private fun items(): MutableList<BaseModel> {
        val items = mutableListOf<BaseModel>()
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 1",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 1"
            )
        )
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 2",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 2"
            )
        )
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 3",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 3"
            )
        )

        items.add(AdsModel("https://www.coolmilk.com/wp-content/uploads/250-newsletter-banner-advert.png"))

        items.add(
            ArticlesModel(
                "Indefinites can also be used to 1",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 1"
            )
        )
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 2",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 2"
            )
        )
        items.add(AdsModel("https://www.coolmilk.com/wp-content/uploads/250-newsletter-banner-advert.png"))
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 1",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 1"
            )
        )
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 2",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 2"
            )
        )
        items.add(AdsModel("https://www.coolmilk.com/wp-content/uploads/250-newsletter-banner-advert.png"))
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 3",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 3"
            )
        )

        items.add(AdsModel("https://www.coolmilk.com/wp-content/uploads/250-newsletter-banner-advert.png"))

        items.add(
            ArticlesModel(
                "Indefinites can also be used to 1",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 1"
            )
        )
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 2",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 2"
            )
        )
        items.add(AdsModel("https://www.coolmilk.com/wp-content/uploads/250-newsletter-banner-advert.png"))
        items.add(
            ArticlesModel(
                "Indefinites can also be used to 12020202020202002",
                "A negative article specifies none of its noun, and can thus be regarded as neither definite nor indefinite 1"
            )
        )

        return items

    }
}