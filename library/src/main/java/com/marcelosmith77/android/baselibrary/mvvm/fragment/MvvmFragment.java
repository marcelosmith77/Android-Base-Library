package com.marcelosmith77.android.baselibrary.mvvm.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.AnyRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcelosmith77.android.baselibrary.fragment.BaseFragment;
import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;
import com.marcelosmith77.android.baselibrary.mvvm.viewmodel.MvvmObservableViewModel;
import com.marcelosmith77.android.baselibrary.mvvm.viewmodel.MvvmViewModel;

import javax.inject.Inject;


public abstract class MvvmFragment<B extends ViewDataBinding, VM extends MvvmViewModel> extends BaseFragment<B> implements MvvmView {

    @Inject
    protected VM viewModel;

    /**
     *  Sets the content view, creates the binding and attaches the view to the view model
     *
     *  Nosso layout possui uma variavel declarada, neste caso, é necessário setar a variável para qque o binding das informações do modelo ocorra com sucesso.
     **/
    protected final View setAndBindContentView(@AnyRes int variableId, @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, @LayoutRes int layoutResID) {
        if(viewModel == null) { throw new IllegalStateException("viewModel must already be set via injection"); }

        try {
            viewModel.attachMvvmView(this, savedInstanceState);
        } catch(ClassCastException e) {
            if (!(viewModel instanceof MvvmObservableViewModel)) {
                throw new RuntimeException(getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + viewModel.getClass().getSimpleName());
            }
        }

        binding = DataBindingUtil.inflate(inflater, layoutResID, container, false);

        onLoadModel();

        binding.setVariable(variableId, viewModel);

        View rootView = binding.getRoot();
        rootView.setClickable(true);

        setupKeyboardBehavior(rootView);

        return rootView;
    }

    protected abstract void onLoadModel();

    /**
     *  Sets the content view, creates the binding
     *
     *  Neste caso, não setamos a variavel model ligada ao data bindings.
     *  A razão disto é quando o layout não tem nenhum bind associado, o modelview apenas servirá para implementar as regras de negocios.
     **/
    protected final View setContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, @LayoutRes int layoutResID) {
        if(viewModel == null) { throw new IllegalStateException("viewModel must already be set via injection"); }

        try {
            viewModel.attachMvvmView(this, savedInstanceState);
        } catch(ClassCastException e) {
            if (!(viewModel instanceof MvvmObservableViewModel)) {
                throw new RuntimeException(getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + viewModel.getClass().getSimpleName());
            }
        }

        binding = DataBindingUtil.inflate(inflater, layoutResID, container, false);

        onLoadModel();

        View rootView = binding.getRoot();
        rootView.setClickable(true);

        setupKeyboardBehavior(rootView);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (viewModel != null)
            viewModel.detachMvvmView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (viewModel != null)
            viewModel.onDestroy();
    }
}
