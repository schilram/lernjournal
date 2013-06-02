package ch.zhaw.schilram.lernjournal.domain;

import java.io.Serializable;

/**
 * @author: rschilling
 */
public class Recipe implements Serializable {


    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String instruction;

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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(final String instruction) {
        this.instruction = instruction;
    }
}