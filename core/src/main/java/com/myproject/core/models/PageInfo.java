
package com.myproject.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import javax.annotation.PostConstruct;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import java.util.Optional;

@Model(adaptables = Resource.class, 
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageInfo {
    
    @SlingObject
    private ResourceResolver resourceResolver;
    
    @SlingObject
    private Resource resource;
    
    private String parentPagePath;
    private String currentPageTitle;
    
    @PostConstruct
    protected void init() {
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        Page currentPage = pageManager.getContainingPage(resource);
        
        if (currentPage != null) {
            // Get page title - tries multiple properties
            currentPageTitle = currentPage.getPageTitle();
            if (currentPageTitle == null || currentPageTitle.isEmpty()) {
                currentPageTitle = currentPage.getTitle();
            }
            if (currentPageTitle == null || currentPageTitle.isEmpty()) {
                currentPageTitle = currentPage.getName();
            }
            
            Page parentPage = currentPage.getParent();
            if (parentPage != null) {
                parentPagePath = parentPage.getPath();
            }
        }
    }
    
    public String getParentPagePath() { return parentPagePath; }
    public String getCurrentPageTitle() { return currentPageTitle; }
}