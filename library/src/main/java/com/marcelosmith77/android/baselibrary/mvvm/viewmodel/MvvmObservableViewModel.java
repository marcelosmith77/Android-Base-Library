package com.marcelosmith77.android.baselibrary.mvvm.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcelosmith77.android.baselibrary.BR;
import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Interface para definir uma View Model
 */
public abstract class MvvmObservableViewModel<M, V extends MvvmView> extends BaseObservable {

    private M model;
    private V mvvmView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void attachMvvmView(V view, @Nullable Bundle savedInstanceState) {
        this.mvvmView = view;
        this.compositeDisposable = new CompositeDisposable();

        if(savedInstanceState != null) { restoreInstanceState(savedInstanceState); }
    }

    public void detachMvvmView() {
        mvvmView = null;
        compositeDisposable.dispose();
        compositeDisposable = null;
    }

    protected void restoreInstanceState(@NonNull Bundle savedInstanceState) { }

    public void onDestroy(){}

    public void setModel(M model) {
        this.model = model;
        notifyPropertyChanged(BR.model);
    }

    @Bindable
    public M getModel() {
        return this.model;
    }

    public V getMvvmView() {
        return mvvmView;
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
