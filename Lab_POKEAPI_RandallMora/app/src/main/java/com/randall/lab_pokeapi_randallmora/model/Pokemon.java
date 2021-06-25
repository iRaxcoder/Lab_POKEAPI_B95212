package com.randall.lab_pokeapi_randallmora.model;

public class Pokemon {
    private int number;
    private String name;
    private String url;

    private String description;

    public String getName() {
        return name;
    }

    public int getNumber() {
        String[] partes = url.split("/");
        return Integer.parseInt(partes[partes.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return "soy el Pokemon numero: "+getNumber()+ " y me llamo: "+getName();
    }

    public void setDescription(String description) {
        this.description=description;
    }
}
