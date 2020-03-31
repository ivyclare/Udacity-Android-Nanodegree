package com.udacity.shoestore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012J\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\u0012J\u0015\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/udacity/shoestore/ShoeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "newDestination", "Landroidx/lifecycle/MutableLiveData;", "", "shoe", "Lcom/udacity/shoestore/models/Shoe;", "getShoe", "()Lcom/udacity/shoestore/models/Shoe;", "setShoe", "(Lcom/udacity/shoestore/models/Shoe;)V", "shoeList", "Ljava/util/ArrayList;", "addShoeToList", "", "createShoe", "getNewDestination", "Landroidx/lifecycle/LiveData;", "getShoeList", "setNewDestination", "destinationId", "(Ljava/lang/Integer;)V", "app_debug"})
public final class ShoeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private com.udacity.shoestore.models.Shoe shoe;
    private final androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.udacity.shoestore.models.Shoe>> shoeList = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> newDestination = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.udacity.shoestore.models.Shoe getShoe() {
        return null;
    }
    
    public final void setShoe(@org.jetbrains.annotations.NotNull()
    com.udacity.shoestore.models.Shoe p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.ArrayList<com.udacity.shoestore.models.Shoe>> getShoeList() {
        return null;
    }
    
    public final void addShoeToList(@org.jetbrains.annotations.NotNull()
    com.udacity.shoestore.models.Shoe shoe) {
    }
    
    public final void createShoe() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getNewDestination() {
        return null;
    }
    
    public final void setNewDestination(@org.jetbrains.annotations.Nullable()
    java.lang.Integer destinationId) {
    }
    
    public ShoeViewModel() {
        super();
    }
}