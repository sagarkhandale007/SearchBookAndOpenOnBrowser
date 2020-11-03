package com.example.gutenbergproject.Model;

import org.json.JSONArray;
import org.json.JSONObject;

public class RowBookDetails {


    private String Title;
    private JSONObject Images_formats_details;
    private JSONArray Authors_details;
    private JSONArray Bookshelves_details;
    private String Name;



    public RowBookDetails(String title, JSONObject images_formats_details, JSONArray authors_details, JSONArray bookshelves_details,String name) {

        Title = title;
        Images_formats_details = images_formats_details;
        Authors_details = authors_details;
        Bookshelves_details = bookshelves_details;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public JSONObject getImages_formats_details() {
        return Images_formats_details;
    }

    public void setImages_formats_details(JSONObject images_formats_details) {
        Images_formats_details = images_formats_details;
    }

    public JSONArray getAuthors_details() {
        return Authors_details;
    }

    public void setAuthors_details(JSONArray authors_details) {
        Authors_details = authors_details;
    }

    public JSONArray getBookshelves_details() {
        return Bookshelves_details;
    }

    public void setBookshelves_details(JSONArray bookshelves_details) {
        Bookshelves_details = bookshelves_details;
    }


}
