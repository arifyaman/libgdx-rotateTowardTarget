package com.xlip.threedtemp.Interfaces;


import java.io.File;

/**
 * Created by Yaman on 20.02.2016.
 */
public interface AndroidUnit {


    void share_score(File f);

    AchievementService getAchievementService();
    LeaderBoardService getLeaderBoardService();
    GameBillingClient getGameClient();

    AdUnit getAdUnit();

    void googleLogin();

    interface AchievementService {
        void triggerAchievements(String key, int times);
        void showAchievements();
        void opened();
        void played();
    }

    interface LeaderBoardService {
        void display(String key);
        void display();
        void pushScore(String key, long score);
    }

    interface AdUnit {
        void showBottomBanner();
        void showInterstitial();
        void hideBottomBanner();
    }

    interface GameBillingClient {
        void tryToBuy(String productId);
        void consume(String token);
    }



}
