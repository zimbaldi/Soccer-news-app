package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover mock de Notícias
        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviária tem desfalque importante", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. "));
        news.add(new News("Ferrinha joga no sábado", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. "));
        news.add(new News("Copa do Mundo feminina está terminando", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. "));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}