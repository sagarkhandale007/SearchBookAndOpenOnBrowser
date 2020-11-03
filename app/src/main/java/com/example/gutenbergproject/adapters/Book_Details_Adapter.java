package com.example.gutenbergproject.adapters;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gutenbergproject.Model.RowBookDetails;
import com.example.gutenbergproject.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Book_Details_Adapter extends RecyclerView.Adapter<Book_Details_Adapter.ViewHolder> {
    private Context mContext;
    private List<RowBookDetails> orderDescItem;
    private Dialog dialog;
    private List<RowBookDetails> arraylist;
    EditText lable_name;
    View focusView = null;
    Button mBtnok,mBtncancel;
    ImageView img;
    //WebView WebView;
    String empId,firstName,lastName,mobileNo;
    Dialog dialog_Status;
    public Book_Details_Adapter(Context context, List listItemDesc) {
        this.mContext = context;
        this.orderDescItem = listItemDesc;
    }

    @NonNull
    @Override
    public Book_Details_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book_name, parent, false);
        return new Book_Details_Adapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return orderDescItem.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final Book_Details_Adapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        viewHolder.itemView.setTag(orderDescItem.get(position));
        final RowBookDetails currentOrder = orderDescItem.get(position);

       /////------------------------ bind image and book&Authors Name-------------------------////////////
        viewHolder.txtBook_Name.setText(currentOrder.getTitle());
        viewHolder.txtAuthors_Name.setText(currentOrder.getName());
        img.setImageResource(R.mipmap.ic_launcher);


       JSONObject IMAGES = currentOrder.getImages_formats_details();
        if (IMAGES.has("image/jpeg")) {
            try {
                String pic = IMAGES.getString("image/jpeg");

                Glide.with(mContext)
                        .load(pic) // image url
                        .placeholder(R.mipmap.ic_launcher) // any placeholder to load at start
                        .error(R.drawable.ic_search)  // any image in case of error

                        .override(114, 162)// resizing
                        .centerCrop()
                        .into(img);  // imageview object

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Glide.with(mContext)
                    .load(R.mipmap.ic_launcher) // image url
                    .placeholder(R.mipmap.ic_launcher) // any placeholder to load at start
                    .error(R.drawable.ic_search)  // any image in case of error
                    .override(114, 162)// resizing
                    .centerCrop()
                    .into(img);  // imageview object


        }



        viewHolder.Info_name_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RowBookDetails currentOrder = orderDescItem.get((viewHolder.getAdapterPosition()));
                JSONObject IMAGES = currentOrder.getImages_formats_details();

           /////------------------------ Open (html,pdf,text) file on web Browser-------------------------////////////
                if (IMAGES.has("text/html; charset=utf-8")){

                    try {
                        String  Html_page =  IMAGES.getString("text/html; charset=utf-8");
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Html_page));
                        mContext.startActivity(browserIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if (IMAGES.has("application/pdf")) {
                    try {
                        String pdf = IMAGES.getString("application/pdf");
                        //WebView mWebView =new WebView(mContext);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf));
                        mContext.startActivity(browserIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

               else if (IMAGES.has("text/plain; charset=utf-8")) {
                    try {
                        String Plan_Text_file = IMAGES.getString("text/plain; charset=utf-8");
                        //WebView mWebView =new WebView(mContext);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Plan_Text_file));
                        mContext.startActivity(browserIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                else  if (IMAGES.has("text/plain; charset=utf-8")) {
                    try {
                        String Plan_Text_file = IMAGES.getString("text/plain; charset=utf-8");
                        //WebView mWebView =new WebView(mContext);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Plan_Text_file));
                        mContext.startActivity(browserIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                /////------------------------ File cannot open -------------------------////////////
              else if (IMAGES.has("application/zip")||IMAGES.has("application/x-mobipocket-ebook")) {
                    try {
                        String ZipFile = IMAGES.getString("application/zip");

                        setAlert();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



                //delete(viewHolder.getAdapterPosition()); //calls the method above to delete
            }
        });
    }
    /////------------------------ show alert fine cannot open -------------------------////////////
    private void setAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.app_name);
        //builder.setIcon(R.drawable.res);
        builder.setMessage("No viewable version available"+"\n"+"Zip File")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
                /*.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/
        AlertDialog alert = builder.create();
        alert.show();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtBook_Name;
        TextView txtAuthors_Name;
        LinearLayout Info_name_author;

        ImageView mBtnItemDelete;
        public ViewHolder(View convertView) {
            super(convertView);



           // imageCardview=convertView.findViewById(R.id.imageCardview);
            Info_name_author=convertView.findViewById(R.id.Info_name_author);
            txtBook_Name = convertView.findViewById(R.id.txtBook_Name);
            img=convertView.findViewById(R.id.BookImages);
            txtAuthors_Name=convertView.findViewById(R.id.txtAuthors_Name);
        }
    }

    public void delete(int position) { //removes the row
        orderDescItem.remove(position);
        notifyItemRemoved(position);

    }


    /////------------------------ filter search books to title or Authors name -------------------------////////////
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<RowBookDetails> results = new ArrayList<RowBookDetails>();
                if (arraylist == null)
                    arraylist = orderDescItem;
                if (constraint != null) {
                    if (arraylist != null && arraylist.size() > 0) {
                        for (final RowBookDetails g : arraylist) {
                            if (g.getName().toLowerCase()
                                    .contains(constraint.toString())|| g.getTitle().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                orderDescItem = (ArrayList<RowBookDetails>) results.values;
                notifyDataSetChanged();
            }
        };
    }


}