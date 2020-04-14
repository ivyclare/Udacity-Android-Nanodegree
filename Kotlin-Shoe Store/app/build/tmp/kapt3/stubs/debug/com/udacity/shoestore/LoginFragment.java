package com.udacity.shoestore;

import java.lang.System;

/**
 * A simple [Fragment] subclass.
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lcom/udacity/shoestore/LoginFragment;", "Landroidx/fragment/app/Fragment;", "()V", "loginBinding", "Lcom/udacity/shoestore/databinding/FragmentLoginBinding;", "getLoginBinding", "()Lcom/udacity/shoestore/databinding/FragmentLoginBinding;", "setLoginBinding", "(Lcom/udacity/shoestore/databinding/FragmentLoginBinding;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "signIn", "", "view", "user", "Lcom/udacity/shoestore/models/User;", "signUp", "Companion", "app_debug"})
public final class LoginFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    public com.udacity.shoestore.databinding.FragmentLoginBinding loginBinding;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EMAIL = "EMAIL";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PASSWORD = "PASSWORD";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SESSION = "SESSION";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGGED = "LOGGED";
    public static final com.udacity.shoestore.LoginFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.udacity.shoestore.databinding.FragmentLoginBinding getLoginBinding() {
        return null;
    }
    
    public final void setLoginBinding(@org.jetbrains.annotations.NotNull()
    com.udacity.shoestore.databinding.FragmentLoginBinding p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    public final void signUp(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    com.udacity.shoestore.models.User user) {
    }
    
    public final void signIn(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    com.udacity.shoestore.models.User user) {
    }
    
    public LoginFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/udacity/shoestore/LoginFragment$Companion;", "", "()V", "EMAIL", "", "LOGGED", "PASSWORD", "SESSION", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}