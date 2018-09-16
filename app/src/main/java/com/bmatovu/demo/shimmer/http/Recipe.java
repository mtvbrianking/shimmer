package com.bmatovu.demo.shimmer.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Recipes Response POJO
 */
public class Recipe {

   @SerializedName("id")
   @Expose
   private Integer id;
   @SerializedName("name")
   @Expose
   private String name;
   @SerializedName("description")
   @Expose
   private String description;
   @SerializedName("price")
   @Expose
   private Integer price;
   @SerializedName("chef")
   @Expose
   private String chef;
   @SerializedName("thumbnail")
   @Expose
   private String thumbnail;
   @SerializedName("timestamp")
   @Expose
   private String timestamp;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getPrice() {
      return price;
   }

   public void setPrice(Integer price) {
      this.price = price;
   }

   public String getChef() {
      return chef;
   }

   public void setChef(String chef) {
      this.chef = chef;
   }

   public String getThumbnail() {
      return thumbnail;
   }

   public void setThumbnail(String thumbnail) {
      this.thumbnail = thumbnail;
   }

   public String getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
   }

}
