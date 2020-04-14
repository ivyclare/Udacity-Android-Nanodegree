package com.udacity.shoestore;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.udacity.shoestore.databinding.FragmentInstructionsBindingImpl;
import com.udacity.shoestore.databinding.FragmentLoginBindingImpl;
import com.udacity.shoestore.databinding.FragmentShoeDetailBindingImpl;
import com.udacity.shoestore.databinding.FragmentWelcomeBindingImpl;
import com.udacity.shoestore.databinding.ShoeItemBindingImpl;
import com.udacity.shoestore.databinding.ShoeListFragmentBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTINSTRUCTIONS = 1;

  private static final int LAYOUT_FRAGMENTLOGIN = 2;

  private static final int LAYOUT_FRAGMENTSHOEDETAIL = 3;

  private static final int LAYOUT_FRAGMENTWELCOME = 4;

  private static final int LAYOUT_SHOEITEM = 5;

  private static final int LAYOUT_SHOELISTFRAGMENT = 6;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(6);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.udacity.shoestore.R.layout.fragment_instructions, LAYOUT_FRAGMENTINSTRUCTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.udacity.shoestore.R.layout.fragment_login, LAYOUT_FRAGMENTLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.udacity.shoestore.R.layout.fragment_shoe_detail, LAYOUT_FRAGMENTSHOEDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.udacity.shoestore.R.layout.fragment_welcome, LAYOUT_FRAGMENTWELCOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.udacity.shoestore.R.layout.shoe_item, LAYOUT_SHOEITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.udacity.shoestore.R.layout.shoe_list_fragment, LAYOUT_SHOELISTFRAGMENT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTINSTRUCTIONS: {
          if ("layout/fragment_instructions_0".equals(tag)) {
            return new FragmentInstructionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_instructions is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGIN: {
          if ("layout/fragment_login_0".equals(tag)) {
            return new FragmentLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_login is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSHOEDETAIL: {
          if ("layout/fragment_shoe_detail_0".equals(tag)) {
            return new FragmentShoeDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_shoe_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWELCOME: {
          if ("layout/fragment_welcome_0".equals(tag)) {
            return new FragmentWelcomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_welcome is invalid. Received: " + tag);
        }
        case  LAYOUT_SHOEITEM: {
          if ("layout/shoe_item_0".equals(tag)) {
            return new ShoeItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for shoe_item is invalid. Received: " + tag);
        }
        case  LAYOUT_SHOELISTFRAGMENT: {
          if ("layout/shoe_list_fragment_0".equals(tag)) {
            return new ShoeListFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for shoe_list_fragment is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(7);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "select");
      sKeys.put(2, "button_select");
      sKeys.put(3, "buttonSelect");
      sKeys.put(4, "user");
      sKeys.put(5, "shoe");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(6);

    static {
      sKeys.put("layout/fragment_instructions_0", com.udacity.shoestore.R.layout.fragment_instructions);
      sKeys.put("layout/fragment_login_0", com.udacity.shoestore.R.layout.fragment_login);
      sKeys.put("layout/fragment_shoe_detail_0", com.udacity.shoestore.R.layout.fragment_shoe_detail);
      sKeys.put("layout/fragment_welcome_0", com.udacity.shoestore.R.layout.fragment_welcome);
      sKeys.put("layout/shoe_item_0", com.udacity.shoestore.R.layout.shoe_item);
      sKeys.put("layout/shoe_list_fragment_0", com.udacity.shoestore.R.layout.shoe_list_fragment);
    }
  }
}
