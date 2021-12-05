package com.example.capstoneprojectpb;

public class ScreenItem {

    String Title, Title_2, Description;
    int ScreenImg;

    public ScreenItem(String title, String title_2, String description, int screenImg) {
        Title = title;
        Title_2 = title_2;
        Description = description;
        ScreenImg = screenImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setTitle2(String title_2) {
        Title_2 = title_2;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getTitle2() {
        return Title_2;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}
