// Generated by data binding compiler. Do not edit!
package com.udacity.shoestore.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.udacity.shoestore.InstructionsFragment;
import com.udacity.shoestore.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentInstructionsBinding extends ViewDataBinding {
  @NonNull
  public final TextView instruction;

  @NonNull
  public final TextView instructions;

  @NonNull
  public final Button nextButton;

  @Bindable
  protected InstructionsFragment mSelect;

  protected FragmentInstructionsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView instruction, TextView instructions, Button nextButton) {
    super(_bindingComponent, _root, _localFieldCount);
    this.instruction = instruction;
    this.instructions = instructions;
    this.nextButton = nextButton;
  }

  public abstract void setSelect(@Nullable InstructionsFragment select);

  @Nullable
  public InstructionsFragment getSelect() {
    return mSelect;
  }

  @NonNull
  public static FragmentInstructionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentInstructionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentInstructionsBinding>inflateInternal(inflater, R.layout.fragment_instructions, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentInstructionsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentInstructionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentInstructionsBinding>inflateInternal(inflater, R.layout.fragment_instructions, null, false, component);
  }

  public static FragmentInstructionsBinding bind(@NonNull View view) {
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
  public static FragmentInstructionsBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentInstructionsBinding)bind(component, view, R.layout.fragment_instructions);
  }
}
