package com.udacity.shoestore.databinding;
import com.udacity.shoestore.R;
import com.udacity.shoestore.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ShoeListFragmentBindingImpl extends ShoeListFragmentBinding implements com.udacity.shoestore.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.shoeScrollView, 2);
        sViewsWithIds.put(R.id.shoeView, 3);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ShoeListFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ShoeListFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[1]
            , (android.widget.ScrollView) bindings[2]
            , (android.widget.LinearLayout) bindings[3]
            );
        this.addButton.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback3 = new com.udacity.shoestore.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.buttonSelect == variableId) {
            setButtonSelect((com.udacity.shoestore.ShoeListFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setButtonSelect(@Nullable com.udacity.shoestore.ShoeListFragment ButtonSelect) {
        this.mButtonSelect = ButtonSelect;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.buttonSelect);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.udacity.shoestore.ShoeListFragment buttonSelect = mButtonSelect;
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.addButton.setOnClickListener(mCallback3);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // buttonSelect
        com.udacity.shoestore.ShoeListFragment buttonSelect = mButtonSelect;
        // buttonSelect != null
        boolean buttonSelectJavaLangObjectNull = false;



        buttonSelectJavaLangObjectNull = (buttonSelect) != (null);
        if (buttonSelectJavaLangObjectNull) {



            buttonSelect.addShoe(callbackArg_0);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): buttonSelect
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}