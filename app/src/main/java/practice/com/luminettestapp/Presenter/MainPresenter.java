package practice.com.luminettestapp.Presenter;

import practice.com.luminettestapp.Model.User;

public class MainPresenter {

    private MainView mainView;
    private User user;

    public MainPresenter(MainView mainView){
        this.mainView = mainView;

        user = new User();
    }

    public User getUser(){
        return this.user;
    }

    public interface MainView{

    }


}
