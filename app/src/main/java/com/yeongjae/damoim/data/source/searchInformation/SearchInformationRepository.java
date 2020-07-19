package com.yeongjae.damoim.data.source.searchInformation;

import com.yeongjae.damoim.data.SearchEmail;
import com.yeongjae.damoim.data.SearchPassword;
import com.yeongjae.damoim.data.VerificationCode;

public class SearchInformationRepository implements SearchInformationSource {

    private static SearchInformationRepository searchInformationRepository;
    private SearchInformationRemoteDataSource searchInformationRemoteDataSource;
    private static VerificationCode verificationCode;

    public static SearchInformationRepository getInstance() {

        if(searchInformationRepository == null) {
            searchInformationRepository = new SearchInformationRepository();
        }

        return searchInformationRepository;
    }

    public static VerificationCode getInstanceVerificationCode() {
        if(verificationCode == null) {
            verificationCode = new VerificationCode();
        }

        return verificationCode;
    }
    private SearchInformationRepository() {
        searchInformationRemoteDataSource = SearchInformationRemoteDataSource.getInstance();
    }

    @Override
    public void callSearchEmail(SearchEmail searchEmail, SearchEmailApiListener listener) {
        searchInformationRemoteDataSource.callSearchEmail(searchEmail, listener);
    }

    @Override
    public void callSearchPassword(SearchPassword searchPassword, SearchPasswordApiListener listener) {
        searchInformationRemoteDataSource.callSearchPassword(searchPassword, listener);
    }
}
