package com.liaudev.catbreedsapp.data.response;


import android.os.Parcel;
import android.os.Parcelable;

public class BreedItem implements Parcelable {
    private String wikipediaUrl;
    private String origin;
    private String description;
    private String lifeSpan;
    private String id;
    private String image;
    private String referenceImage;
    private int healthIssues;
    private int adaptability;
    private int intelligence;
    private String temperament;
    private String name;
    private int energyLevel;
    private int affectionLevel;

    public BreedItem() {
    }

    protected BreedItem(Parcel in) {
        wikipediaUrl = in.readString();
        origin = in.readString();
        description = in.readString();
        lifeSpan = in.readString();
        id = in.readString();
        image = in.readString();
        referenceImage = in.readString();
        healthIssues = in.readInt();
        adaptability = in.readInt();
        intelligence = in.readInt();
        temperament = in.readString();
        name = in.readString();
        energyLevel = in.readInt();
        affectionLevel = in.readInt();
    }

    public static final Creator<BreedItem> CREATOR = new Creator<BreedItem>() {
        @Override
        public BreedItem createFromParcel(Parcel in) {
            return new BreedItem(in);
        }

        @Override
        public BreedItem[] newArray(int size) {
            return new BreedItem[size];
        }
    };

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReferenceImage() {
        return referenceImage;
    }

    public void setReferenceImage(String referenceImage) {
        this.referenceImage = referenceImage;
    }

    public int getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(int healthIssues) {
        this.healthIssues = healthIssues;
    }


    public int getAdaptability() {
        return adaptability;
    }

    public void setAdaptability(int adaptability) {
        this.adaptability = adaptability;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getAffectionLevel() {
        return affectionLevel;
    }

    public void setAffectionLevel(int affectionLevel) {
        this.affectionLevel = affectionLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(wikipediaUrl);
        parcel.writeString(origin);
        parcel.writeString(description);
        parcel.writeString(lifeSpan);
        parcel.writeString(id);
        parcel.writeString(image);
        parcel.writeString(referenceImage);
        parcel.writeInt(healthIssues);
        parcel.writeInt(adaptability);
        parcel.writeInt(intelligence);
        parcel.writeString(temperament);
        parcel.writeString(name);
        parcel.writeInt(energyLevel);
        parcel.writeInt(affectionLevel);
    }
}
