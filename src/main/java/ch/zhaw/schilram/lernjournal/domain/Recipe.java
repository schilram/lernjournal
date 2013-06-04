package ch.zhaw.schilram.lernjournal.domain;

/**
 * @author schilram
 */
public class Recipe extends StorableItem {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String instructions;

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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }
}
