package com.carsland.carslandrest.objects;

import javax.xml.bind.annotation.XmlRootElement;

//You will need to create a Java Object. Jersey uses these to contruct requests and responses.

public class Car {
  
  private int id;
  private String modelId;
  private String price;
  private String mileage;
  private String year;
  private String make;
  private String model;
  private String color;
  private String image;
  private String image1;
  private String image2;
  private String image3;
  private String features;
  private String specs;
  
  public String getPrice() {
      return price;
  }

  public String getMileage() {
      return mileage;
  }

  public int getId() {
      return id;
  }
  
  public String getModelId() {
      return modelId;
  }
  
  public String getYear() {
      return year;
  }
  
  public String getMake() {
      return make;
  }
  
  public String getModel() {
      return model;
  }
  
  public String getColor() {
      return color;
  }
  
  public String getImage() {
      return image;
  }
  
  public String getImage1() {
      return image1;
  }
  
  public String getImage2() {
      return image2;
  }
  
  public String getImage3() {
      return image3;
  }
  
  public String getFeatures() {
      return features;
  }
  
  public String getSpecs() {
      return specs;
  }
  
  public void setPrice(String price) {
      this.price = price;
  }

  public void setMileage(String mileage) {
      this.mileage = mileage;
  }

  public void setId(int id) {
      this.id = id;
  }
  
  public void setModelId(String modelId) {
      this.modelId = modelId;
  }
  
  public void setYear(String year) {
      this.year = year;
  }
  
  public void setMake(String make) {
      this.make = make;
  }
  
  public void setModel(String model) {
      this.model = model;
  }
  
  public void setColor(String color) {
      this.color = color;
  }
  
  public void setImage(String image) {
      this.image = image;
  }
  
  public void setImage1(String image1) {
      this.image1 = image1;
  }
  
  public void setImage2(String image2) {
      this.image2 = image2;
  }
  
  public void setImage3(String image3) {
      this.image3 = image3;
  }
  
  public void setFeatures(String features) {
      this.features = features;
  }
  
  public void setSpecs(String specs) {
      this.specs = specs;
  }
}
