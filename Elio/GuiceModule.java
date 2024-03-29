
package com.codeuptopia.secondhandmarket.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.codeuptopia.secondhandmarket.controller.ItemController;
import com.codeuptopia.secondhandmarket.controller.MessageController;
import com.codeuptopia.secondhandmarket.controller.PaymentController;
import com.codeuptopia.secondhandmarket.controller.ReviewController;
import com.codeuptopia.secondhandmarket.controller.SearchController;
import com.codeuptopia.secondhandmarket.controller.UserController;
import com.codeuptopia.secondhandmarket.dao.ItemDao;
import com.codeuptopia.secondhandmarket.dao.MessageDao;
import com.codeuptopia.secondhandmarket.dao.ReviewDao;
import com.codeuptopia.secondhandmarket.dao.UserDao;
import com.codeuptopia.secondhandmarket.database.DatabaseConnection;
import com.codeuptopia.secondhandmarket.database.DatabaseProperties;
import com.codeuptopia.secondhandmarket.model.Item;
import com.codeuptopia.secondhandmarket.model.Message;
import com.codeuptopia.secondhandmarket.model.Payment;
import com.codeuptopia.secondhandmarket.model.Review;
import com.codeuptopia.secondhandmarket.model.User;
import com.codeuptopia.secondhandmarket.security.AuthRealm;
import com.codeuptopia.secondhandmarket.security.JwtFilter;
import com.codeuptopia.secondhandmarket.security.JwtUtil;
import com.codeuptopia.secondhandmarket.service.AnalyticsService;
import com.codeuptopia.secondhandmarket.service.ItemService;
import com.codeuptopia.secondhandmarket.service.MessageService;
import com.codeuptopia.secondhandmarket.service.PaymentService;
import com.codeuptopia.secondhandmarket.service.ReportService;
import com.codeuptopia.secondhandmarket.service.ReviewService;
import com.codeuptopia.secondhandmarket.service.SearchService;
import com.codeuptopia.secondhandmarket.service.UserService;

public class GuiceModule extends AbstractModule {
    
    private final AppConfig appConfig;
    
    public GuiceModule(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
    
    @Override
    protected void configure() {
        // Bind services
        bind(ItemService.class).in(Singleton.class);
        bind(UserService.class).in(Singleton.class);
        bind(MessageService.class).in(Singleton.class);
        bind(PaymentService.class).in(Singleton.class);
        bind(SearchService.class).in(Singleton.class);
        bind(ReviewService.class).in(Singleton.class);
        bind(AnalyticsService.class).in(Singleton.class);
        bind(ReportService.class).in(Singleton.class);
        
        // Bind DAOs
        bind(ItemDao.class).in(Singleton.class);
        bind(UserDao.class).in(Singleton.class);
        bind(MessageDao.class).in(Singleton.class);
        bind(ReviewDao.class).in(Singleton.class);
        
        // Bind controllers
        bind(ItemController.class).in(Singleton.class);
        bind(UserController.class).in(Singleton.class);
        bind(MessageController.class).in(Singleton.class);
        bind(PaymentController.class).in(Singleton.class);
        bind(SearchController.class).in(Singleton.class);
        bind(ReviewController.class).in(Singleton.class);
        
        // Bind security
        bind(AuthRealm.class).in(Singleton.class);
        bind(JwtFilter.class).in(Singleton.class);
        bind(JwtUtil.class).in(Singleton.class);
        
        // Bind database
        bind(DatabaseProperties.class).toInstance(new DatabaseProperties(appConfig.getDatabaseUrl(), appConfig.getDatabaseUsername(), appConfig.getDatabasePassword()));
        bind(DatabaseConnection.class).in(Singleton.class);
        
        // Bind models
        bind(Item.class).in(Singleton.class);
        bind(User.class).in(Singleton.class);
        bind(Message.class).in(Singleton.class);
        bind(Payment.class).in(Singleton.class);
        bind(Review.class).in(Singleton.class);
    }
    
    @Provides
    public AppConfig provideAppConfig()
    {
        return appConfig;
    }
    
}
