package com.ivy.bakingapp.data.model;

/**
 * Created by Ivoline-Clarisse on 11/7/2017.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IngredientModel implements Parcelable
{

    @SerializedName("quantity")
    @Expose
    private double quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    public final static Parcelable.Creator<IngredientModel> CREATOR = new Creator<IngredientModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IngredientModel createFromParcel(Parcel in) {
            return new IngredientModel(in);
        }

        public IngredientModel[] newArray(int size) {
            return (new IngredientModel[size]);
        }

    }
            ;

    protected IngredientModel(Parcel in) {
        this.quantity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.measure = ((String) in.readValue((String.class.getClassLoader())));
        this.ingredient = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public IngredientModel() {
    }

    /**
     *
     * @param measure
     * @param ingredient
     * @param quantity
     */




    public IngredientModel(Double quantity, String measure, String ingredient) {
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(quantity);
        dest.writeValue(measure);
        dest.writeValue(ingredient);
    }

    public int describeContents() {
        return 0;
    }

}