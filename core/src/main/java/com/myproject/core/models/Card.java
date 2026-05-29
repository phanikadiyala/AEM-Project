package com.myproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.ValueMap;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class Card {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String linkUrl;

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

/*
public String getImagePath() {
    return image != null ? image : "/libs/dam/placeholder.png";
}
*/
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * Returns true if the card has required data (title + image)
     
    public boolean isValid() {
        return title != null && !title.isEmpty() 
            && image != null && !image.isEmpty();
    }
*/

}