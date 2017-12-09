package com.weblogin.gallery;



import com.weblogin.gallery.beans.ImageBean;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;


import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named
@ConversationScoped
public class ImagesView implements Serializable{

  private final String path = "http://localhost:8080/img/t.jpg";
  private List<ImageBean> images;
  private ImageBean selectedImage;

  @PostConstruct
  public void init() {
    images = new ArrayList<>();
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Normal","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));
    images.add(new ImageBean("Stefan","t.jpg", "Lalaland"));

    System.out.println("Construct ImageHandler");

  }

  public List<ImageBean> getImages() {
    return images;
  }

  public ImageBean getSelectedImage() {
    return selectedImage;
  }

  public void setSelectedImage(ImageBean selectedImage) {
    this.selectedImage = selectedImage;
  }
}
