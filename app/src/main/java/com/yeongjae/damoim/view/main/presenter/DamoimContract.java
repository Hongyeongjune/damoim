package com.yeongjae.damoim.view.main.presenter;

public interface DamoimContract {

    interface View {
        void showErrorMessage(String message);
        void startLocationVerification();
        void startTownActivity();
        void startBoardActivity();
        void startTradeActivity();
        void startPeopleActivity();
    }

}
