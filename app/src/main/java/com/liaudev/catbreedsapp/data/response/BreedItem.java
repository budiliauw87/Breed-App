package com.liaudev.catbreedsapp.data.response;


import android.os.Parcel;
import android.os.Parcelable;

public class BreedItem implements Parcelable {
    private int suppressedTail;
    private String wikipediaUrl;
    private String origin;
    private String description;
    private int experimental;
    private String lifeSpan;
    private String cfaUrl;
    private int rare;
    private String countryCodes;
    private String id;
    private int shortLegs;
    private int sheddingLevel;
    private String image;
    private int dogFriendly;
    private int natural;
    private int rex;
    private int healthIssues;
    private int hairless;
    private String altNames;
    private int adaptability;
    private int vocalisation;
    private int intelligence;
    private int socialNeeds;
    private String countryCode;
    private int childFriendly;
    private String vcahospitalsUrl;
    private String temperament;
    private String name;
    private String vetstreetUrl;
    private int grooming;
    private int hypoallergenic;
    private int indoor;
    private int energyLevel;
    private int strangerFriendly;
    private String referenceImageId;
    private int affectionLevel;
    private int lap;

    public BreedItem() {
    }

    protected BreedItem(Parcel in) {
        suppressedTail = in.readInt();
        wikipediaUrl = in.readString();
        origin = in.readString();
        description = in.readString();
        experimental = in.readInt();
        lifeSpan = in.readString();
        cfaUrl = in.readString();
        rare = in.readInt();
        countryCodes = in.readString();
        id = in.readString();
        shortLegs = in.readInt();
        sheddingLevel = in.readInt();
        image = in.readString();
        dogFriendly = in.readInt();
        natural = in.readInt();
        rex = in.readInt();
        healthIssues = in.readInt();
        hairless = in.readInt();
        altNames = in.readString();
        adaptability = in.readInt();
        vocalisation = in.readInt();
        intelligence = in.readInt();
        socialNeeds = in.readInt();
        countryCode = in.readString();
        childFriendly = in.readInt();
        vcahospitalsUrl = in.readString();
        temperament = in.readString();
        name = in.readString();
        vetstreetUrl = in.readString();
        grooming = in.readInt();
        hypoallergenic = in.readInt();
        indoor = in.readInt();
        energyLevel = in.readInt();
        strangerFriendly = in.readInt();
        referenceImageId = in.readString();
        affectionLevel = in.readInt();
        lap = in.readInt();
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

    public int getSuppressedTail() {
        return suppressedTail;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDescription() {
        return description;
    }

    public int getExperimental() {
        return experimental;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getCfaUrl() {
        return cfaUrl;
    }

    public int getRare() {
        return rare;
    }

    public String getCountryCodes() {
        return countryCodes;
    }

    public String getId() {
        return id;
    }

    public int getShortLegs() {
        return shortLegs;
    }

    public int getSheddingLevel() {
        return sheddingLevel;
    }

    public String getImage() {
        return image;
    }

    public int getDogFriendly() {
        return dogFriendly;
    }

    public int getNatural() {
        return natural;
    }

    public int getRex() {
        return rex;
    }

    public int getHealthIssues() {
        return healthIssues;
    }

    public int getHairless() {
        return hairless;
    }

    public String getAltNames() {
        return altNames;
    }

    public int getAdaptability() {
        return adaptability;
    }

    public int getVocalisation() {
        return vocalisation;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getSocialNeeds() {
        return socialNeeds;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getChildFriendly() {
        return childFriendly;
    }

    public String getVcahospitalsUrl() {
        return vcahospitalsUrl;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getName() {
        return name;
    }

    public String getVetstreetUrl() {
        return vetstreetUrl;
    }

    public int getGrooming() {
        return grooming;
    }

    public int getHypoallergenic() {
        return hypoallergenic;
    }

    public int getIndoor() {
        return indoor;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public int getStrangerFriendly() {
        return strangerFriendly;
    }

    public String getReferenceImageId() {
        return referenceImageId;
    }

    public int getAffectionLevel() {
        return affectionLevel;
    }

    public int getLap() {
        return lap;
    }


    public void setSuppressedTail(int suppressedTail) {
        this.suppressedTail = suppressedTail;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExperimental(int experimental) {
        this.experimental = experimental;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setCfaUrl(String cfaUrl) {
        this.cfaUrl = cfaUrl;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }

    public void setCountryCodes(String countryCodes) {
        this.countryCodes = countryCodes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShortLegs(int shortLegs) {
        this.shortLegs = shortLegs;
    }

    public void setSheddingLevel(int sheddingLevel) {
        this.sheddingLevel = sheddingLevel;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDogFriendly(int dogFriendly) {
        this.dogFriendly = dogFriendly;
    }

    public void setNatural(int natural) {
        this.natural = natural;
    }

    public void setRex(int rex) {
        this.rex = rex;
    }

    public void setHealthIssues(int healthIssues) {
        this.healthIssues = healthIssues;
    }

    public void setHairless(int hairless) {
        this.hairless = hairless;
    }

    public void setAltNames(String altNames) {
        this.altNames = altNames;
    }

    public void setAdaptability(int adaptability) {
        this.adaptability = adaptability;
    }

    public void setVocalisation(int vocalisation) {
        this.vocalisation = vocalisation;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setSocialNeeds(int socialNeeds) {
        this.socialNeeds = socialNeeds;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setChildFriendly(int childFriendly) {
        this.childFriendly = childFriendly;
    }

    public void setVcahospitalsUrl(String vcahospitalsUrl) {
        this.vcahospitalsUrl = vcahospitalsUrl;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVetstreetUrl(String vetstreetUrl) {
        this.vetstreetUrl = vetstreetUrl;
    }

    public void setGrooming(int grooming) {
        this.grooming = grooming;
    }

    public void setHypoallergenic(int hypoallergenic) {
        this.hypoallergenic = hypoallergenic;
    }

    public void setIndoor(int indoor) {
        this.indoor = indoor;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void setStrangerFriendly(int strangerFriendly) {
        this.strangerFriendly = strangerFriendly;
    }

    public void setReferenceImageId(String referenceImageId) {
        this.referenceImageId = referenceImageId;
    }

    public void setAffectionLevel(int affectionLevel) {
        this.affectionLevel = affectionLevel;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(suppressedTail);
        parcel.writeString(wikipediaUrl);
        parcel.writeString(origin);
        parcel.writeString(description);
        parcel.writeInt(experimental);
        parcel.writeString(lifeSpan);
        parcel.writeString(cfaUrl);
        parcel.writeInt(rare);
        parcel.writeString(countryCodes);
        parcel.writeString(id);
        parcel.writeInt(shortLegs);
        parcel.writeInt(sheddingLevel);
        parcel.writeString(image);
        parcel.writeInt(dogFriendly);
        parcel.writeInt(natural);
        parcel.writeInt(rex);
        parcel.writeInt(healthIssues);
        parcel.writeInt(hairless);
        parcel.writeString(altNames);
        parcel.writeInt(adaptability);
        parcel.writeInt(vocalisation);
        parcel.writeInt(intelligence);
        parcel.writeInt(socialNeeds);
        parcel.writeString(countryCode);
        parcel.writeInt(childFriendly);
        parcel.writeString(vcahospitalsUrl);
        parcel.writeString(temperament);
        parcel.writeString(name);
        parcel.writeString(vetstreetUrl);
        parcel.writeInt(grooming);
        parcel.writeInt(hypoallergenic);
        parcel.writeInt(indoor);
        parcel.writeInt(energyLevel);
        parcel.writeInt(strangerFriendly);
        parcel.writeString(referenceImageId);
        parcel.writeInt(affectionLevel);
        parcel.writeInt(lap);
    }
}
