package com.example.gutenbergproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.example.gutenbergproject.Model.RowBookDetails;
import com.example.gutenbergproject.adapters.Book_Details_Adapter;
import com.example.gutenbergproject.utils.JsonObjectRequestWithHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.gutenbergproject.utils.service.books_API;

public class Second_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Context mContext = this;
    public static RecyclerView tabLayoutItemList;
    private Book_Details_Adapter adapter;
    private static List<RowBookDetails> rowItems;
    View focusView = null;
    private ProgressDialog progressBar;
    public SearchView auto_complete_SearchOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
        //  hideKeyboard(MainActivity.this);
        auto_complete_SearchOrder=(SearchView)findViewById(R.id.auto_complete_SearchOrder);
        auto_complete_SearchOrder.setOnQueryTextListener(this);
        auto_complete_SearchOrder.setQueryHint("Search");

        auto_complete_SearchOrder.setActivated(true);
        auto_complete_SearchOrder.setQueryHint("Search");
        //  auto_complete_SearchOrder.onActionViewExpanded();
        //auto_complete_SearchOrder.setTextcolor();
        auto_complete_SearchOrder.setIconified(false);
        auto_complete_SearchOrder.clearFocus();

        tabLayoutItemList = findViewById(R.id.tab_list_book);

        rowItems = new ArrayList<>();
        getAllBooks();



/////------------------------ bind data to adpter -------------------------////////////
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        int spanCount = 3; // 3 columns
        boolean includeEdge = false;
        tabLayoutItemList.setLayoutManager(mLayoutManager);
        tabLayoutItemList.addItemDecoration(new GridSpacingItemDecoration(spanCount, 10, includeEdge));
        tabLayoutItemList.setItemAnimator(new DefaultItemAnimator());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        tabLayoutItemList.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        adapter = new Book_Details_Adapter(Second_Activity.this, rowItems);
        tabLayoutItemList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    /////------------------------ call API for books details -------------------------////////////
    public void getAllBooks() {

        progressBar = new ProgressDialog(mContext, R.style.MyAlertDialogStyle);
        progressBar.setCancelable(true);
        progressBar.setMessage("Waiting For Books...");
        // Set horizontal progress bar style.
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // Set progress dialog icon.
        progressBar.setIcon(R.mipmap.ic_launcher_error);
        // Set progress dialog title.
        progressBar.setTitle("Please wait");
        // Whether progress dialog can be canceled or not.
        progressBar.setCancelable(true);
        // When user touch area outside progress dialog whether the progress dialog will be canceled or not.
        progressBar.setCanceledOnTouchOutside(false);
        progressBar.show();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonObjectRequestWithHeader jsonObjReq = new JsonObjectRequestWithHeader(Request.Method.GET,
                books_API, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Toast.makeText(mContext, response.toString(), Toast.LENGTH_SHORT).show();
                        try {
                            // dissmissProgress();
                            progressBar.dismiss();
                           // JSONObject book_Data = response.getJSONObject();
                            //JSONObject Lable_Data = response.getJSONObject();
                            String name = null;
                            JSONArray json_book = response.getJSONArray("results");


                            for (int p = 0; p < json_book.length(); p++) {
                                try {
                                    JSONObject Item_book_Details = json_book.getJSONObject(p);

                                    String title = Item_book_Details.getString("title");
                                    JSONObject Images_formats_Details = Item_book_Details.getJSONObject("formats");
                                    JSONArray bookshelves_details = Item_book_Details.getJSONArray("bookshelves");
                                    JSONArray authors_details = Item_book_Details.getJSONArray("authors");
                                   // JSONArray authors_details = Item_book_Details.getJSONArray("authors");

                                    for (int b = 0; b < authors_details.length(); b++) {
                                        try {
                                        JSONObject authors_book_Details = authors_details.getJSONObject(b);

                                        if (authors_book_Details.has("name")){
                                            name = authors_book_Details.getString("name");
                                        }
                                            RowBookDetails item = new RowBookDetails(title, Images_formats_Details,authors_details,bookshelves_details,name);
                                            rowItems.add(item);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Response", "Error: " + error.getMessage());
                String err = (error.getMessage() == null) ? "Error" : error.getMessage();
                progressBar.dismiss();
                Log.e("Error", err);
            }


        });


        int socketTimeout = 50000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);
        queue.add(jsonObjReq);






    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
            int spanCount = 3; // 3 columns
            boolean includeEdge = false;
            tabLayoutItemList.setLayoutManager(mLayoutManager);
            tabLayoutItemList.addItemDecoration(new GridSpacingItemDecoration(spanCount, 10, includeEdge));
            tabLayoutItemList.setItemAnimator(new DefaultItemAnimator());
            adapter = new Book_Details_Adapter(this, rowItems);
            tabLayoutItemList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            if(adapter!=null){
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
            }
        }
        return false;
    }

    /////------------------------ adjuest RecyclerView view  -------------------------////////////
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Second_Activity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
        startActivity(intent, bndlanimation);
        finish();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.back_event:
                Intent intent = new Intent(Second_Activity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.pull_in_left, R.anim.pull_out_right).toBundle();
                startActivity(intent, bndlanimation);
                finish();
                break;
            default:
                break;
        }

        return true;
    }
}
