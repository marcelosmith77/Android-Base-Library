package com.marcelosmith77.android.baselibrary.activity;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;

/**
 * Define meios de navegação básicos entre acitivies e fragmentos
 */
public interface INavigator {


    /**
     * Exibe um fragmento, limpando a pilha corrente, e adicionando o mesmo a pilha
     * @param f
     */
    void showFrament(Fragment f);

    /**
     * Exibe um fragmento, limpando a pilha corrente, e adicionando o mesmo a pilha
     * @param f
     * @param name
     */
    void showFrament(Fragment f, String name);

    /**
     * Exibe um fragmento
     * @param f - Fragmento
     * @param addToStack - Adicionar a pilha?
     * @param clearStack - limpar a pilha corrente?
     */
    void showFrament(Fragment f, boolean addToStack, boolean clearStack);

    /**
     * Exibe um fragmento
     * @param f - Fragmento
     * @param name - nome do fragmento
     * @param addToStack - Adicionar a pilha?
     * @param clearStack - limpar a pilha corrente?
     */
    void showFrament(Fragment f, String name, boolean addToStack, boolean clearStack);

    /**
     * Exibe um fragmento
     * @param f - Fragmento
     * @param name - nome do fragmento
     * @param addToStack - Adicionar a pilha?
     * @param clearStack - limpar a pilha corrente?
     * @param args - parâmetros
     */
    void showFrament(Fragment f, String name, boolean addToStack, boolean clearStack, Bundle args);


    /**
     * Exibe um fragmento
     * @param f - Fragmento
     * @param addToStack - Adicionar a pilha?
     * @param clearStack - limpar a pilha corrente?
     * @param args - parâmetros
     */
    void showFrament(Fragment f, boolean addToStack, boolean clearStack, Bundle args);

    /**
     * Exibe um fragmento
     * @param f - Fragmento
     * @param addToStack - Adicionar a pilha?
     * @param clearStack - limpar a pilha corrente?
     * @param key - Chave associada ao parâmetro
     * @param parcelable - Objeto parâmetro
     */
    void showFrament(Fragment f, boolean addToStack, boolean clearStack, String key, Parcelable parcelable);

    /**
     * Executa ação back button pressed
     */
    void back();
}
