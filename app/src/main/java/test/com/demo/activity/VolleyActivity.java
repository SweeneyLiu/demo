package test.com.demo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import test.com.demo.R;

public class VolleyActivity extends BaseActivity {

    private TextView mTextView;
    private RequestQueue mRequestQueue;
    private static final String URL = "http://v.juhe.cn/weather/index?";
    private static final String TAG = VolleyActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initViews();
        mRequestQueue = Volley.newRequestQueue(this);

       /* StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                mTextView.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,error.toString());
            }
        });*/

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(VolleyActivity.this,"请求成功",Toast.LENGTH_SHORT);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this,"请求失败",Toast.LENGTH_SHORT);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String,String>();
                String city = "温州";
                try {
                    city = new String(city.getBytes(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                map.put("cityname",city);
                map.put("key","7e7e7a5031b08edc4505cb1c7dd56c8f");
                return map;
            }
        };

        mRequestQueue.add(stringRequest);
    }

    @Override
    protected void initViews() {
        mTextView = (TextView)findViewById(R.id.tv);
    }
}
