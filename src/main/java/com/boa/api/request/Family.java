package com.boa.api.request;

public class Family {
    private String name, genre, classe;
    private Integer age;

    public Family() {
    }

    public Family(String name, String genre, String classe, Integer age) {
        this.name = name;
        this.genre = genre;
        this.classe = classe;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getClasse() {
        return this.classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Family name(String name) {
        this.name = name;
        return this;
    }

    public Family genre(String genre) {
        this.genre = genre;
        return this;
    }

    public Family classe(String classe) {
        this.classe = classe;
        return this;
    }

    public Family age(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", genre='" + getGenre() + "'" +
            ", classe='" + getClasse() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }

}
