package com.example.rfid.config;

import com.example.rfid.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    public final static String SESSION_KEY = "user";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).excludePathPatterns("/dataAnalysis/Author/getMasterpiece3/{userId}/{startYear}/{endYear}","/dataAnalysis/Author/getMasterpiece1/{userId}/{startYear}/{endYear}","/dataAnalysis/Author/getMasterpiece2/{userId}/{startYear}/{endYear}","/dataAnalysis/Author/getCitedByYear/{userId}","/dataAnalysis/Author/getAuthorNameInstitute/{conference}","/dataAnalysis/Institute/getAuthor/{institute}","/dataAnalysis/Institute/getResearch/{institute}","/dataAnalysis/Institute/getPaper/{institute}","/institutionList","/institution","/dataAnalysis/Author/getCited/{userId}","/adminHome","/adminLogin","/conferenceList","/conference","/authorList","/author","/institutionList","/institution","/fieldList","/field","/dataAnalysis/Field/getFieldList","/dataAnalysis/Author/getResearchPieChart/{userId}","/dataAnalysis/**","/dataControl/upload","/dataControl/**","/oasisInfoPage","/oasisInfoDetail","/getMostAuthor/{content}", "/getMostAffiliation/{content}", "/search","/search/searchByMulti","/login", "/index", "/signUp", "/register", "/error", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/**/*.jpeg", "/font/**").addPathPatterns("/**");
    }
}
