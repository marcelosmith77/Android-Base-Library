package com.marcelosmith77.android.baselibrary.adapter.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import com.marcelosmith77.android.baselibrary.mvvm.view.MvvmView;

/**
 * Abstração do RecyclerView, para utilização do recurso de data mvvm
 *
 * Realiza o bind do model definido na variavel "viewModel" do layout fornecido.
 * Para funcionamento correto a variável definida na tag <data> deve utilizar o nome "viewModel" Ex: <variable name="viewModel" ...
 *
 * @see BaseRecyclerViewHolder
 */
public abstract class BaseRecyclerViewAdapter<VH extends BaseRecyclerViewHolder,M, V extends MvvmView> extends RecyclerView.Adapter<VH> {

    private V view;

    @NonNull
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return onCreateViewHolder(binding, view);
    }

    protected abstract VH onCreateViewHolder(ViewDataBinding binding, V view);

    /**
     * Recupera o view model da posicao e realiza o bind do layout com o modelo
     *
     * @param holder
     * @param position
     */
    public void onBindViewHolder(VH holder, int position) {
        M model = getModelForPosition(position);
        holder.bind(model);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    /**
     * Retorna o pojo viewmodel para a posição.
     *
     * É possivel utilizar N layouts para compor a lista.
     * @param position
     * @return
     */
//    protected abstract M getModelForPosition(int position);

    protected M getModelForPosition(int position) {
        return getItems().get(position);
    }

    public abstract void setItems(List<M> items);

    public abstract List<M> getItems();

    /**
     * Retorna o layout xml para inflar para a posição
     *
     * É possivel utilizar N layouts para compor a lista.
     *
     * @param position
     * @return layout resource id
     */
    protected abstract int getLayoutIdForPosition(int position);


    /**
     * Binds view corrente para este adapter
     */
    public void attachMvvmView(V view) {
        this.view = view;
    }

    /**
     * limpa view
     */
    public void detachMvvmView() {

        if (getItems() != null){
            getItems().clear();
        }

        view = null;
    }
}