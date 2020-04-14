package com.udacity.shoestore.databinding;
import com.udacity.shoestore.R;
import com.udacity.shoestore.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ShoeItemBindingImpl extends ShoeItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener shoeDescriptionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of shoe.description
            //         is shoe.setDescription((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(shoeDescription);
            // localize variables for thread safety
            // shoe
            com.udacity.shoestore.models.Shoe shoe = mShoe;
            // shoe.description
            java.lang.String shoeDescription = null;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (shoe) != (null);
            if (shoeJavaLangObjectNull) {




                shoe.setDescription(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener shoeNameandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of shoe.name
            //         is shoe.setName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(shoeName);
            // localize variables for thread safety
            // shoe
            com.udacity.shoestore.models.Shoe shoe = mShoe;
            // shoe.name
            java.lang.String shoeName = null;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (shoe) != (null);
            if (shoeJavaLangObjectNull) {




                shoe.setName(((java.lang.String) (callbackArg_0)));
            }
        }
    };

    public ShoeItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ShoeItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.shoeDescription.setTag(null);
        this.shoeName.setTag(null);
        setRootTag(root);
        // listeners
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
        if (BR.shoe == variableId) {
            setShoe((com.udacity.shoestore.models.Shoe) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setShoe(@Nullable com.udacity.shoestore.models.Shoe Shoe) {
        this.mShoe = Shoe;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.shoe);
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
        com.udacity.shoestore.models.Shoe shoe = mShoe;
        java.lang.String ShoeName1 = null;
        java.lang.String ShoeDescription1 = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (shoe != null) {
                    // read shoe.name
                    ShoeName1 = shoe.getName();
                    // read shoe.description
                    ShoeDescription1 = shoe.getDescription();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.shoeDescription, ShoeDescription1);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.shoeName, ShoeName1);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.shoeDescription, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, shoeDescriptionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.shoeName, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, shoeNameandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): shoe
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}