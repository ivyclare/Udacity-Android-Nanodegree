// Generated by data binding compiler. Do not edit!
package com.udacity.shoestore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.udacity.shoestore.R;
import com.udacity.shoestore.ShoeDetailFragment;
import com.udacity.shoestore.models.Shoe;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentShoeDetailBinding extends ViewDataBinding {
  @NonNull
  public final Button cancelButton;

  @NonNull
  public final EditText company;

  @NonNull
  public final TextView companyName;

  @NonNull
  public final EditText description;

  @NonNull
  public final TextView descriptionText;

  @NonNull
  public final Button saveButton;

  @NonNull
  public final EditText shoeEdit;

  @NonNull
  public final TextView shoeName;

  @NonNull
  public final EditText size;

  @NonNull
  public final TextView sizeLabel;

  @Bindable
  protected Shoe mShoe;

  @Bindable
  protected ShoeDetailFragment mButtonSelect;

  protected FragmentShoeDetailBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button cancelButton, EditText company, TextView companyName, EditText description,
      TextView descriptionText, Button saveButton, EditText shoeEdit, TextView shoeName,
      EditText size, TextView sizeLabel) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cancelButton = cancelButton;
    this.company = company;
    this.companyName = companyName;
    this.description = description;
    this.descriptionText = descriptionText;
    this.saveButton = saveButton;
    this.shoeEdit = shoeEdit;
    this.shoeName = shoeName;
    this.size = size;
    this.sizeLabel = sizeLabel;
  }

  public abstract void setShoe(@Nullable Shoe shoe);

  @Nullable
  public Shoe getShoe() {
    return mShoe;
  }

  public abstract void setButtonSelect(@Nullable ShoeDetailFragment button_select);

  @Nullable
  public ShoeDetailFragment getButtonSelect() {
    return mButtonSelect;
  }

  @NonNull
  public static FragmentShoeDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentShoeDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentShoeDetailBinding>inflateInternal(inflater, R.layout.fragment_shoe_detail, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentShoeDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentShoeDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentShoeDetailBinding>inflateInternal(inflater, R.layout.fragment_shoe_detail, null, false, component);
  }

  public static FragmentShoeDetailBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentShoeDetailBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentShoeDetailBinding)bind(component, view, R.layout.fragment_shoe_detail);
  }
}