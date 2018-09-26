package com.example.jessi.myapplication00003;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private List<Model_Product> listModel_Product;

    Model_Product modelp = new Model_Product();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String productURL = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=ac56d9b1f49a843b9be57f8d2796ea35&user_id=1389";

        listModel_Product = new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.POST,
                productURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            //REQUEST AN ARRAY FROM THE FIRST JSON OBJECT(response) CALLED catagory
                            JSONArray catagory = response.getJSONArray("category");

                            for(int i = 0; i < catagory.length(); i++)
                            {
                                //CREATE JSON OBJECT (catObject) AND PLACE ONE OBJECT FROM catagory
                                JSONObject catObject = catagory.getJSONObject(i);

                                //CREATE MODEL(modelp)

                                //PLACE ALL ITEMS IN catObject into MODEL
                                modelp.setId(catObject.getString("cid"));
                                modelp.setName(catObject.getString("cname"));
                                modelp.setDescription(catObject.getString("cdiscription"));
                                modelp.setUrl(catObject.getString("cimagerl"));

                                //ADD MODEL(modelp) INTO A LIST
                                listModel_Product.add(modelp);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes so that it renders the list view with updated data
                        //CPLA.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        Log.d(TAG, "onCreate: LIST SIZE ============================================================LIST SIZE : " +  listModel_Product.size());












    }

    public class Model_Product {
        private static final String TAG = "Model_Product";
        private String id;
        private String name;
        private String description;
        private String url;

        public Model_Product()
        {

        }
        public Model_Product(String id, String name, String description, String url) {
            Log.d(TAG, "Model_Product: =============================NEW MODEL CALL");

            this.id = id;
            this.name = name;
            this.description = description;
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
