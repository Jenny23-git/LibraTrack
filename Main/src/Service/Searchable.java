package Service;

/**
 * Defines the searching functionality for library objects.
 */
public interface Searchable {

    /**
     * Checks whether an object matches a given search keyword.
     *
     * @param keyword the keyword used for searching
     * @return true if the object matches the keyword, otherwise false
     */
    boolean matches(String keyword);

}