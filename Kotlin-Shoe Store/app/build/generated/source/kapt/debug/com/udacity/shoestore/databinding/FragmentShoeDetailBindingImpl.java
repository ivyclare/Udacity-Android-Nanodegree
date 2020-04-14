package com.udacity.shoestore.databinding;
import com.udacity.shoestore.R;
import com.udacity.shoestore.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentShoeDetailBindingImpl extends FragmentShoeDetailBinding implements com.udacity.shoestore.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.shoeName, 7);
        sViewsWithIds.put(R.id.companyName, 8);
        sViewsWithIds.put(R.id.sizeLabel, 9);
        sViewsWithIds.put(R.id.descriptionText, 10);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener companyandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of shoe.company
            //         is shoe.setCompany((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(company);
            // localize variables for thread safety
            // shoe
            com.udacity.shoestore.models.Shoe shoe = mShoe;
            // shoe.company
            java.lang.String shoeCompany = null;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (shoe) != (null);
            if (shoeJavaLangObjectNull) {




                shoe.setCompany(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener descriptionandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of shoe.description
            //         is shoe.setDescription((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(description);
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
    private androidx.databinding.InverseBindingListener shoeEditandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of shoe.name
            //         is shoe.setName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(shoeEdit);
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
    private androidx.databinding.InverseBindingListener sizeandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of ("") + (shoe.size)
            //         is shoe.setSize((double) androidx.databinding.ViewDataBinding.parse(callbackArg_0, shoe.size))
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(size);
            // localize variables for thread safety
            // shoe.size
            double shoeSize = 0.0;
            // ("") + (shoe.size)
            java.lang.String javaLangStringShoeSize = null;
            // shoe
            com.udacity.shoestore.models.Shoe shoe = mShoe;
            // androidx.databinding.ViewDataBinding.parse(callbackArg_0, shoe.size)
            double androidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = 0.0;
            // (double) androidx.databinding.ViewDataBinding.parse(callbackArg_0, shoe.size)
            double doubleAndroidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = 0.0;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (shoe) != (null);
            if (shoeJavaLangObjectNull) {





                shoeSize = shoe.getSize();

                androidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = androidx.databinding.ViewDataBinding.parse(callbackArg_0, shoeSize);

                doubleAndroidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = ((double) (androidxDatabindingViewDataBindingParseCallbackArg0ShoeSize));

                shoe.setSize(doubleAndroidxDatabindingViewDataBindingParseCallbackArg0ShoeSize);
            }
        }
    };

    public FragmentShoeDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private FragmentShoeDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[5]
            , (android.widget.EditText) bindings[2]
            , (android.widget.TextView) bindings[8]
            , (android.widget.EditText) bindings[4]
            , (android.widget.TextView) bindings[10]
            , (android.widget.Button) bindings[6]
            , (android.widget.EditText) bindings[1]
            , (android.widget.TextView) bindings[7]
            , (android.widget.EditText) bindings[3]
            , (android.widget.TextView) bindings[9]
            );
        this.cancelButton.setTag(null);
        this.company.setTag(null);
        this.description.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.saveButton.setTag(null);
        this.shoeEdit.setTag(null);
        this.size.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new com.udacity.shoestore.generated.callback.OnClickListener(this, 2);
        mCallback1 = new com.udacity.shoestore.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.button_select == variableId) {
            setButtonSelect((com.udacity.shoestore.ShoeDetailFragment) variable);
        }
        else if (BR.shoe == variableId) {
            setShoe((com.udacity.shoestore.models.Shoe) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setButtonSelect(@Nullable com.udacity.shoestore.ShoeDetailFragment ButtonSelect) {
        this.mButtonSelect = ButtonSelect;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.button_select);
        super.requestRebind();
    }
    public void setShoe(@Nullable com.udacity.shoestore.models.Shoe Shoe) {
        this.mShoe = Shoe;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
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
        java.lang.String javaLangStringShoeSize = null;
        java.lang.String ShoeName1 = null;
        double shoeSize = 0.0;
        com.udacity.shoestore.ShoeDetailFragment buttonSelect = mButtonSelect;
        com.udacity.shoestore.models.Shoe shoe = mShoe;
        java.lang.String shoeCompany = null;
        java.lang.String shoeDescription = null;

        if ((dirtyFlags & 0x6L) != 0) {



                if (shoe != null) {
                    // read shoe.name
                    ShoeName1 = shoe.getName();
                    // read shoe.size
                    shoeSize = shoe.getSize();
                    // read shoe.company
                    shoeCompany = shoe.getCompany();
                    // read shoe.description
                    shoeDescription = shoe.getDescription();
                }


                // read ("") + (shoe.size)
                javaLangStringShoeSize = ("") + (shoeSize);
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.cancelButton.setOnClickListener(mCallback1);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.company, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, companyandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.description, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, descriptionandroidTextAttrChanged);
            this.saveButton.setOnClickListener(mCallback2);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.shoeEdit, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, shoeEditandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.size, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, sizeandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.company, shoeCompany);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.description, shoeDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.shoeEdit, ShoeName1);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.size, javaLangStringShoeSize);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // button_select
                com.udacity.shoestore.ShoeDetailFragment buttonSelect = mButtonSelect;
                // shoe
                com.udacity.shoestore.models.Shoe shoe = mShoe;
                // button_select != null
                boolean buttonSelectJavaLangObjectNull = false;



                buttonSelectJavaLangObjectNull = (buttonSelect) != (null);
                if (buttonSelectJavaLangObjectNull) {




                    buttonSelect.save(callbackArg_0, shoe);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // button_select
                com.udacity.shoestore.ShoeDetailFragment buttonSelect = mButtonSelect;
                // button_select != null
                boolean buttonSelectJavaLangObjectNull = false;



                buttonSelectJavaLangObjectNull = (buttonSelect) != (null);
                if (buttonSelectJavaLangObjectNull) {



                    buttonSelect.cancel(callbackArg_0);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): button_select
        flag 1 (0x2L): shoe
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}