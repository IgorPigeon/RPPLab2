package com.example.Lab2;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Lab2.Technologies.Technology;

import com.google.gson.Gson;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Technologies> technologiesMLD;

    public LiveData<Technologies> getTechnologiesMLD() {
        return technologiesMLD;

    }

    public int getSizeTechnologies(){
        return technologiesMLD.getValue().getTechnologies().size();
    }

    public Technology getTechnologyIndex(int index){
        if ((index >= 0)&&(index < getSizeTechnologies())) {
            return technologiesMLD.getValue().getTechnologies().get(index);
        }
        else{
            return null;
        }
    }

    public void loadTechnologies(String jsonString, Resources res){
        if(technologiesMLD == null) {
            technologiesMLD = new MutableLiveData<Technologies>();
            Gson g = new Gson();
            Technologies tempTech = g.fromJson(jsonString.toString(), Technologies.class);
            technologiesMLD.setValue(tempTech);

            // Загрузка картинок
            int sizeTech = technologiesMLD.getValue().getTechnologies().size();
            for(int i=0; i<sizeTech; i++){

                // Получаем название картинки
                String nameImage = technologiesMLD.getValue().getTechnologies().get(i).getGraphic();

                // убираем расширение из названия в картинке (4 последних символа)
                int lengthName = nameImage.length();
                nameImage = nameImage.substring(0, lengthName-4);

                // Получаем код изображения
                int codeImage;
                try {
                    codeImage = R.drawable.class.getDeclaredField(nameImage).getInt(res);
                }catch (Exception e){
                    codeImage = R.drawable.university; //если картинка не найдена используем эту
                }

                // Сохраняем изображение
                technologiesMLD.getValue().getTechnologies().get(i).setImage(
                            BitmapFactory.decodeResource(res, codeImage
                        )
                );
            }
        }
    }
}
