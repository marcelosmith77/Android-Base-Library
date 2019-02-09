package com.marcelosmith77.android.baselibrary.mvvm.viewmodel;

import com.marcelosmith77.android.baselibrary.activity.INavigator;
import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;

public abstract class MvvmViewModel<T, V extends MvvmView> extends MvvmObservableViewModel<T, V> {

    /**
     * Ponte para navegar entre os fragmentos e activites do sistema
     */
    protected INavigator navigator;

    public MvvmViewModel(INavigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Fecha o repositório de dados do banco realm
     */
    @Override
    public void onDestroy() {
    }

    /**
     * Seta instãncia do objeto navigator
     * @param navigator
     */
    public void setNavigator(INavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void setModel(T model) {

        super.setModel(model);

        // faz com que o data binding entenda que houve altearção no model
        notifyChange();
    }
}
