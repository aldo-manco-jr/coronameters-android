package org.aldomanco.coronameters.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private Object[] mParams;

    public ViewModelFactory(Application application, Object... params) {
        mApplication = application;
        mParams = params;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == ItalyStatsViewModel.class) {
            return (T) new ItalyStatsViewModel(mApplication, (String) mParams[0], (String) mParams[1]);
        } else {
            return super.create(modelClass);
        }
    }
}
