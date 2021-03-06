package ch.zhaw.schilram.lernjournal.domain;

import java.io.Serializable;

/**
 * @author schilram
 */
public class Ingredient extends StorableItem {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Flavour flavour;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public void setFlavour(final Flavour flavour) {
        this.flavour = flavour;
    }

    public enum Flavour {
        SALTY, SWEET, SOUR, BITTER, UMAMI
    }
}
