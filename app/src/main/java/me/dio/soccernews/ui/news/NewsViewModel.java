package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.SoccerNewsRepository;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    public enum State{
        DOING, DONE, ERROR
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();


    public NewsViewModel() {
        this.findNews();
    }

    private void findNews() {
        state.setValue(State.DOING);
        SoccerNewsRepository.getInstance().getRemoteApi().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()){
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                state.setValue(State.ERROR);
            }
        });
    }

    public void saveNews(News news){
        SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news);
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }

    public LiveData<State> getState() { return this.state; }
}