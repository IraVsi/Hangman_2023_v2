package models.datastructures;

/**
 * Data structure for database words (table words)
 */
public record DataWords(int id, String word, String category) {
    public DataWords(int id, String word, String category) {
        this.id = id;
        this.word = word;
        this.category = category;
    }

    public int getId() {return id; }

    public String getCategory(){return category; }

    public String getWord(){return word; }
}
