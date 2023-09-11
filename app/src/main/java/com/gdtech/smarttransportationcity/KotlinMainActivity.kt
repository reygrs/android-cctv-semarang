package com.gdtech.smarttransportationcity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdtech.smarttransportationcity.adapter.AdapterListCctv
import com.gdtech.smarttransportationcity.api.CctvApi
import com.gdtech.smarttransportationcity.model.Data
import kotlinx.android.synthetic.main.activity_main_cctv.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KotlinMainActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var cctvApi: CctvApi
    private lateinit var adapterListCctv: AdapterListCctv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cctv)
        initRetrofit()
        doLoadListCctv()
    }

    private fun doLoadListCctv() {
        progress_bar_loading_activity_main.visibility = View.VISIBLE
        recycler_view_cctv_activity_main.visibility = View.GONE
        CoroutineScope(Dispatchers.IO).launch {
            val response = cctvApi.getListCctv()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val cctvData = response.body()!!
                        adapterListCctv =
                            AdapterListCctv(cctvData.data, object : AdapterListCctv.ListenerAdapterListCctv {
                                override fun onClickItem(data: Data) {
                                    val intentCctvViewActivity = Intent(this@KotlinMainActivity, CctvViewActivity::class.java)
                                    intentCctvViewActivity.putExtra("url", data.url)
                                    startActivity(intentCctvViewActivity)
                                }
                            })
                        recycler_view_cctv_activity_main.layoutManager = LinearLayoutManager(this@KotlinMainActivity)
                        recycler_view_cctv_activity_main.addItemDecoration(
                            DividerItemDecoration(
                                this@KotlinMainActivity,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                        recycler_view_cctv_activity_main.adapter = adapterListCctv
                        progress_bar_loading_activity_main.visibility = View.GONE
                        recycler_view_cctv_activity_main.visibility = View.VISIBLE
                    } else {
                        progress_bar_loading_activity_main.visibility = View.GONE
                        recycler_view_cctv_activity_main.visibility = View.GONE
                        Toast.makeText(this@KotlinMainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@KotlinMainActivity, "Exception: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://lalin.dishub.semarangkota.go.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        cctvApi = retrofit.create(CctvApi::class.java)
    }
}
