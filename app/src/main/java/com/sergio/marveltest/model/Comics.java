package com.sergio.marveltest.model;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comics implements Serializable {
    @SerializedName("available")
    @Expose
    private int available;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<Object> items = null;
    @SerializedName("returned")
    @Expose
    private int returned;

    /**
     * No args constructor for use in serialization
     *
     */
    public Comics() {
    }

    /**
     *
     * @param items
     * @param collectionURI
     * @param available
     * @param returned
     */
    public Comics(int available, String collectionURI, List<Object> items, int returned) {
        super();
        this.available = available;
        this.collectionURI = collectionURI;
        this.items = items;
        this.returned = returned;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }
}
