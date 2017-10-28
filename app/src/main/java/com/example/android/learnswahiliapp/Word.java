package com.example.android.learnswahiliapp;

import android.media.MediaPlayer;

/**
 * Created by kibo on 8/31/17.
 */

public class Word {
    private String menglishWord;
    private String mswahiliWord;
    private int imageRecourceId = NO_IMAGE;

    public int getMaudioResourceId() {
        return maudioResourceId;
    }

    private int maudioResourceId;


    private static final int NO_IMAGE = -1;

    public Word(String englishWord, String swahiliWord, int audioresourceId) {
        this.menglishWord = englishWord;
        this.mswahiliWord = swahiliWord;
        this.maudioResourceId = audioresourceId;
    }

    public Word(String englishWord, String swahiliWord, int imageResource, int audioResourceId) {
        this.menglishWord = englishWord;
        this.mswahiliWord = swahiliWord;
        this.imageRecourceId = imageResource;
        this.maudioResourceId = audioResourceId;
    }


    public void setImageRecourceId(int imageRecourceId) {
        this.imageRecourceId = imageRecourceId;
    }

    public void setEnglishWord(String englishWord) {

        this.menglishWord = englishWord;
    }

    public void setSwahiliWord(String swahiliWord) {
        this.mswahiliWord = swahiliWord;
    }

    public String getEnglishWord() {

        return menglishWord;
    }

    public String getSwahiliWord() {
        return mswahiliWord;
    }

    public int getImageRecourceId() {
        return imageRecourceId;
    }

    public boolean hasImage() {
        return imageRecourceId != NO_IMAGE;
    }

    @Override
    public String toString() {
        return "Word{" +
                "menglishWord='" + menglishWord + '\'' +
                ", mswahiliWord='" + mswahiliWord + '\'' +
                ", imageRecourceId=" + imageRecourceId +
                ", maudioResourceId=" + maudioResourceId +
                '}';
    }
}
