package com.example.fetch;

public class ItemsListClass  {
    String id;
    String name;
    String listId;

    public ItemsListClass(String id, String name, String listId) {
        this.id = id;
        this.name = name;
        this.listId = listId;
    }

    public ItemsListClass() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }


    }

