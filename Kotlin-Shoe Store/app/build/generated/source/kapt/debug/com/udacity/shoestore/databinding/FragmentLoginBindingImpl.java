package com.udacity.shoestore.databinding;
import com.udacity.shoestore.R;
import com.udacity.shoestore.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentLoginBindingImpl extends FragmentLoginBinding implements com.udacity.shoestore.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.guideline, 5);
        sViewsWithIds.put(R.id.passwordLabel, 6);
        sViewsWithIds.put(R.id.barrier, 7);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener emailandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.email.get()
            //         is user.email.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(email);
            // localize variables for thread safety
            // user.email
            androidx.databinding.ObservableField<java.lang.String> userEmail = null;
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.udacity.shoestore.models.User user = mUser;
            // user.email.get()
            java.lang.String userEmailGet = null;
            // user.email != null
            boolean userEmailJavaLangObjectNull = false;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {


                userEmail = user.getEmail();

                userEmailJavaLangObjectNull = (userEmail) != (null);
                if (userEmailJavaLangObjectNull) {




                    userEmail.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener passwordandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of user.password.get()
            //         is user.password.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(password);
            // localize variables for thread safety
            // user != null
            boolean userJavaLangObjectNull = false;
            // user
            com.udacity.shoestore.models.User user = mUser;
            // user.password != null
            boolean userPasswordJavaLangObjectNull = false;
            // user.password.get()
            java.lang.String userPasswordGet = null;
            // user.password
            androidx.databinding.ObservableField<java.lang.String> userPassword = null;



            userJavaLangObjectNull = (user) != (null);
            if (userJavaLangObjectNull) {


                userPassword = user.getPassword();

                userPasswordJavaLangObjectNull = (userPassword) != (null);
                if (userPasswordJavaLangObjectNull) {




                    userPassword.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentLoginBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private FragmentLoginBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (androidx.constraintlayout.widget.Barrier) bindings[7]
            , (android.widget.EditText) bindings[2]
            , (androidx.constraintlayout.widget.Guideline) bindings[5]
            , (android.widget.Button) bindings[4]
            , (android.widget.EditText) bindings[3]
            , (android.widget.TextView) bindings[6]
            , (android.widget.Button) bindings[1]
            );
        this.email.setTag(null);
        this.loginButton.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.password.setTag(null);
        this.signupButton.setTag(null);
        setRootTag(root);
        // listeners
        mCallback5 = new com.udacity.shoestore.generated.callback.OnClickListener(this, 2);
        mCallback4 = new com.udacity.shoestore.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
        if (BR.user == variableId) {
            setUser((com.udacity.shoestore.models.User) variable);
        }
        else if (BR.select == variableId) {
            setSelect((com.udacity.shoestore.LoginFragment) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUser(@Nullable com.udacity.shoestore.models.User User) {
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }
    public void setSelect(@Nullable com.udacity.shoestore.LoginFragment Select) {
        this.mSelect = Select;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.select);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUserPassword((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeUserEmail((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUserPassword(androidx.databinding.ObservableField<java.lang.String> UserPassword, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserEmail(androidx.databinding.ObservableField<java.lang.String> UserEmail, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
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
        com.udacity.shoestore.models.User user = mUser;
        java.lang.String userPasswordGet = null;
        androidx.databinding.ObservableField<java.lang.String> userPassword = null;
        androidx.databinding.ObservableField<java.lang.String> userEmail = null;
        com.udacity.shoestore.LoginFragment select = mSelect;
        java.lang.String userEmailGet = null;

        if ((dirtyFlags & 0x17L) != 0) {


            if ((dirtyFlags & 0x15L) != 0) {

                    if (user != null) {
                        // read user.password
                        userPassword = user.getPassword();
                    }
                    updateRegistration(0, userPassword);


                    if (userPassword != null) {
                        // read user.password.get()
                        userPasswordGet = userPassword.get();
                    }
            }
            if ((dirtyFlags & 0x16L) != 0) {

                    if (user != null) {
                        // read user.email
                        userEmail = user.getEmail();
                    }
                    updateRegistration(1, userEmail);


                    if (userEmail != null) {
                        // read user.email.get()
                        userEmailGet = userEmail.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x16L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.email, userEmailGet);
        }
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.email, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, emailandroidTextAttrChanged);
            this.loginButton.setOnClickListener(mCallback5);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.password, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, passwordandroidTextAttrChanged);
            this.signupButton.setOnClickListener(mCallback4);
        }
        if ((dirtyFlags & 0x15L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.password, userPasswordGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 2: {
                // localize variables for thread safety
                // user
                com.udacity.shoestore.models.User user = mUser;
                // select
                com.udacity.shoestore.LoginFragment select = mSelect;
                // select != null
                boolean selectJavaLangObjectNull = false;



                selectJavaLangObjectNull = (select) != (null);
                if (selectJavaLangObjectNull) {




                    select.signIn(callbackArg_0, user);
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // user
                com.udacity.shoestore.models.User user = mUser;
                // select
                com.udacity.shoestore.LoginFragment select = mSelect;
                // select != null
                boolean selectJavaLangObjectNull = false;



                selectJavaLangObjectNull = (select) != (null);
                if (selectJavaLangObjectNull) {




                    select.signIn(callbackArg_0, user);
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user.password
        flag 1 (0x2L): user.email
        flag 2 (0x3L): user
        flag 3 (0x4L): select
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}