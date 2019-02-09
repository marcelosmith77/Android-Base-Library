package com.marcelosmith77.android.baselibrary.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.marcelosmith77.android.baselibrary.KeyboardUtils;
import com.marcelosmith77.android.easydrawer.fragment.AbstractBaseDrawerFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment<B extends ViewDataBinding> extends AbstractBaseDrawerFragment {

    private final static String PARAM_SAVED_STATE = "savedState";
    protected B binding;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (this instanceof HasFragmentState) {
            restoreStateFromArguments();
        }
    }

    /**
     *  Sets the content view, creates the binding
     *
     *  Neste caso, não setamos a variavel model ligada ao data bindings.
     *  A razão disto é quando o layout não tem nenhum bind associado, o modelview apenas servirá para implementar as regras de negocios.
     **/
    protected View setContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState, @LayoutRes int layoutResID) {
        binding = DataBindingUtil.inflate(inflater, layoutResID, container, false);

        View rootView = binding.getRoot();
        rootView.setClickable(true);

        setupKeyboardBehavior(rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (this instanceof HasFragmentState) {
            saveStateToArguments();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.dispose();
        compositeDisposable.clear();
    }


    public void setupKeyboardBehavior(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    KeyboardUtils.hideKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupKeyboardBehavior(innerView);
            }
        }
    }

    protected <T> T getPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return (T) preferences.getAll().get(key);
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    /**
     * Salva o estado para o bundle de argumentos
     */
    private void saveStateToArguments() {
        Bundle savedState = new Bundle();

        if (getView() != null)
            onSaveState(savedState);

        if (savedState != null) {
            Bundle b = getArguments();
            if (b != null) {
                b.putBundle(PARAM_SAVED_STATE, savedState);
            }
        }
    }

    /**
     * Restaura o estado a partir dos argumentos
     */
    private void restoreStateFromArguments() {
        Bundle b = getArguments();
        if (b != null) {
            Bundle savedState = b.getBundle(PARAM_SAVED_STATE);
            if (savedState != null) {
                onRestoreState(savedState);
            }
        }
    }

    /**
     * Permite ao fragmento manipular o que deverá ser salvo no estado.
     * @param bundle
     */
    protected void onSaveState(Bundle bundle){}

    /**
     * Repassa ao fragmento o bundle completo obtido do estado
     * @param bundle
     */
    protected void onRestoreState(Bundle bundle){}
}
