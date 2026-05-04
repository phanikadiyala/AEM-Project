package com.myproject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Sling Model for multifield component
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AboutUsNavigation {

    private static final Logger LOGGER = LoggerFactory.getLogger(AboutUsNavigation.class);

    @Inject
    private Resource aboutusnavigation;

    private List<NavigationItem> items;

    @PostConstruct
    protected void init() {
        items = new ArrayList<>();
        
        LOGGER.debug("Initializing AboutUsNavigation");
        
        if (aboutusnavigation != null) {
            LOGGER.debug("Found aboutusnavigation resource at: {}", aboutusnavigation.getPath());
            for (Resource child : aboutusnavigation.getChildren()) {
                LOGGER.debug("Processing child: {}", child.getPath());
                NavigationItem item = child.adaptTo(NavigationItem.class);
                if (item != null) {
                    items.add(item);
                    LOGGER.debug("Added navigation item: {}", item.getSubNavTitle());
                } else {
                    LOGGER.warn("Failed to adapt resource to NavigationItem: {}", child.getPath());
                }
            }
        } else {
            LOGGER.warn("aboutusnavigation resource not found");
        }
        
        LOGGER.debug("Total navigation items loaded: {}", items.size());
    }

    /**
     * Get navigation items from multifield
     */
    public List<NavigationItem> getNavigationItems() {
        return items;
    }

    /**
     * Navigation Item model
     */
    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class NavigationItem {

        private static final Logger LOGGER = LoggerFactory.getLogger(NavigationItem.class);

        @Inject
        private String subNavTitle;

        @Inject
        private String subNavUrl;

        @PostConstruct
        protected void init() {
            LOGGER.debug("NavigationItem - subNavTitle: {}, subNavUrl: {}", subNavTitle, subNavUrl);
        }

        public String getSubNavTitle() {
            return subNavTitle;
        }

        public String getSubNavUrl() {
            return subNavUrl;
        }
    }
}