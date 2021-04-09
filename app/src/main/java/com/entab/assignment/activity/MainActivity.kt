package com.entab.assignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.NoConnectionError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.entab.assignment.R
import com.entab.assignment.adapter.DetailAdapter
import com.entab.assignment.modle.DetailModel
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var detail_list: RecyclerView
    private lateinit var userList: ArrayList<DetailModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList<DetailModel>()
        detail_list = findViewById(R.id.detail_list) as RecyclerView
        detail_list.layoutManager = LinearLayoutManager(this)
        detail_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getRequest()

    }

    private fun getRequest() {

          //showProgressDialog(this, "Loading Class...")

        val Base_URL ="https://gorest.co.in/public-api/users"

            val mStrRequest: StringRequest = object : StringRequest(Method.GET, Base_URL,
                Response.Listener { response ->
                   // utilobj.hideProgressDialog()
                    Log.d("VOLLEY_RESPONSE_CLASS", response)
                    Log.d("URL", Base_URL)
                    val jsonObject: JSONObject
                    val jArray: JSONArray

                    try {
                        if (response != null && response.length > 0) {
                            jsonObject = JSONObject(response)
                            val responseCode = jsonObject.optString("code")
                            if (responseCode.equals("200")) {
                                try {
                                    jArray = jsonObject.getJSONArray("data")
                                    if (jArray != null && jArray.length() > 0) {
                                        for (i in 0..jArray.length()) {
                                            val jsonObject1: JSONObject
                                            jsonObject1 = jArray.getJSONObject(i)

                                            val d_List = DetailModel(
                                                jsonObject1.getString("name"),
                                                jsonObject1.getString("email"),
                                                jsonObject1.getString("gender"),
                                                jsonObject1.getString("status")
                                            )

                                            userList!!.add(d_List)

                                            val adapter = DetailAdapter(this, userList!!)
                                            detail_list!!.adapter = adapter
                                        }

                                    }
                                } catch (e: java.lang.Exception) {

                                }
                            } else {

                            }

                        }

                    } catch (e: java.lang.Exception) {

                    }


                },
                Response.ErrorListener { error ->
                  //  utilobj.hideProgressDialog()
                    if (error is NoConnectionError) {
                    } else {
                    }
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    return HashMap()
                }

                override fun getParams(): Map<String, String> {
                    return HashMap()
                }


            }
            mStrRequest.retryPolicy = DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

            //creating a request queue
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(mStrRequest)
        }
    }
