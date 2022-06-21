package com.example.cinemates.model;

import java.util.ArrayList;

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 10:05
 */
public class Images {
    private int id;
    private ArrayList<Image> backdrops;
    private ArrayList<Image> posters;

    public Images(int id, ArrayList<Image> backdrops, ArrayList<Image> posters) {
        this.backdrops = backdrops;
        this.posters = posters;
        this.id = id;
    }

    public ArrayList<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Image> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Image> posters) {
        this.posters = posters;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", backdrops=" + backdrops +
                ", posters=" + posters +
                '}';
    }

    /**
     * @author Antonio Di Nuzzo
     * Created 19/06/2022 at 11:00
     */
    public class Image {
        private String file_path;
        private int height, width;

        public Image(String file_path, int height, int width) {
            this.file_path = file_path;
            this.height = height;
            this.width = width;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public String toString() {
            return "Image{" +
                    "file_path='" + file_path + '\'' +
                    ", height=" + height +
                    ", width=" + width +
                    '}';
        }
    }
}
