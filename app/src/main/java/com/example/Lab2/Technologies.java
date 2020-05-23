package com.example.Lab2;

import android.graphics.Bitmap;

import java.util.List;

public class Technologies {
    private List<Technology> technologies;

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
    public List<Technology> getTechnologies() {
        return technologies;
    }

    // Класс с технологией
    public static class Technology {
        private String graphic;
        private String name;
        private String helptext;
        private Bitmap image;

        public Technology(){
            graphic = "";
            name = "";
            helptext = "";
            image = null;
        }

        @Override
        public String toString() {
            return "Technology {name:"+name+"; graphic:"+graphic+"; helptext:"+helptext+"}";
        }

        // Сеттеры
        public void setGraphic(String graphic) { this.graphic = graphic;}
        public void setName(String name) { this.name = name; }
        public void setHelptext(String helptext) { this.helptext = helptext; }
        public void setImage(Bitmap image) { this.image = image; }

        // Геттеры
        public String getGraphic() { return graphic; }
        public String getName() { return name; }
        public Bitmap getImage() { return image; }
        public String getHelptext() { return helptext; }
    }
}

