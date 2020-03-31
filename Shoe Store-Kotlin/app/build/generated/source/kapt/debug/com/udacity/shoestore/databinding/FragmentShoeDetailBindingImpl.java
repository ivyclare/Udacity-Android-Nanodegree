package com.udacity.shoestore.databinding;
import com.udacity.shoestore.R;
import com.udacity.shoestore.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentShoeDetailBindingImpl extends FragmentShoeDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.shoeName, 5);
        sViewsWithIds.put(R.id.companyName, 6);
        sViewsWithIds.put(R.id.sizeLabel, 7);
        sViewsWithIds.put(R.id.descriptionText, 8);
        sViewsWithIds.put(R.id.cancelButton, 9);
        sViewsWithIds.put(R.id.saveButton, 10);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
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
            com.udacity.shoestore.models.Shoe Shoe1 = mShoe;
            // shoe.company
            java.lang.String shoeCompany = null;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (Shoe1) != (null);
            if (shoeJavaLangObjectNull) {




                Shoe1.setCompany(((java.lang.String) (callbackArg_0)));
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
            com.udacity.shoestore.models.Shoe Shoe1 = mShoe;
            // shoe.description
            java.lang.String shoeDescription = null;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (Shoe1) != (null);
            if (shoeJavaLangObjectNull) {




                Shoe1.setDescription(((java.lang.String) (callbackArg_0)));
            }
        }
    };
    private androidx.databinding.InverseBindingListener shoeandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of shoe.name
            //         is shoe.setName((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(shoe);
            // localize variables for thread safety
            // shoe
            com.udacity.shoestore.models.Shoe Shoe1 = mShoe;
            // shoe.name
            java.lang.String shoeName = null;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (Shoe1) != (null);
            if (shoeJavaLangObjectNull) {




                Shoe1.setName(((java.lang.String) (callbackArg_0)));
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
            com.udacity.shoestore.models.Shoe Shoe1 = mShoe;
            // androidx.databinding.ViewDataBinding.parse(callbackArg_0, shoe.size)
            double androidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = 0.0;
            // (double) androidx.databinding.ViewDataBinding.parse(callbackArg_0, shoe.size)
            double doubleAndroidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = 0.0;
            // shoe != null
            boolean shoeJavaLangObjectNull = false;



            shoeJavaLangObjectNull = (Shoe1) != (null);
            if (shoeJavaLangObjectNull) {





                shoeSize = Shoe1.getSize();

                androidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = androidx.databinding.ViewDataBinding.parse(callbackArg_0, shoeSize);

                doubleAndroidxDatabindingViewDataBindingParseCallbackArg0ShoeSize = ((double) (androidxDatabindingViewDataBindingParseCallbackArg0ShoeSize));

                Shoe1.setSize(doubleAndroidxDatabindingViewDataBindingParseCallbackArg0ShoeSize);
            }
        }
    };

    public FragmentShoeDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private FragmentShoeDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[9]
            , (android.widget.EditText) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (android.widget.EditText) bindings[4]
            , (android.widget.TextView) bindings[8]
            , (android.widget.Button) bindings[10]
            , (android.widget.EditText) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.EditText) bindings[3]
            , (android.widget.TextView) bindings[7]
            );
        this.company.setTag(null);
        this.description.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.shoe.setTag(null);
        this.size.setTag(null);
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
        java.lang.String javaLangStringShoeSize = null;
        java.lang.String ShoeName1 = null;
        double shoeSize = 0.0;
        com.udacity.shoestore.models.Shoe Shoe1 = mShoe;
        java.lang.String shoeCompany = null;
        java.lang.String shoeDescription = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (Shoe1 != null) {
                    // read shoe.name
                    ShoeName1 = Shoe1.getName();
                    // read shoe.size
                    shoeSize = Shoe1.getSize();
                    // read shoe.company
                    shoeCompany = Shoe1.getCompany();
                    // read shoe.description
                    shoeDescription = Shoe1.getDescription();
                }


                // read ("") + (shoe.size)
                javaLangStringShoeSize = ("") + (shoeSize);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.company, shoeCompany);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.description, shoeDescription);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.shoe, ShoeName1);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.size, javaLangStringShoeSize);
        }
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.company, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, companyandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.description, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, descriptionandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.shoe, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, shoeandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.size, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, sizeandroidTextAttrChanged);
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