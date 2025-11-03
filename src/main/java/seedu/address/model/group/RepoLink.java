package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Group's GitHub repository link in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class RepoLink {

    public static final String MESSAGE_CONSTRAINTS = "Repository link format: [Protocol]Domain Name[Path]\n"
            + "Example: https://github.com/username/repo\n"
            + "- Total URL Maximum length: 200 characters\n- Protocol (optional): can be http:// or https://\n"
            + "- Domain Name (required): must be valid, including a top-level domain (e.g., .com, .org)\n"
            + "- Path (optional): if present, must start with / and contain no spaces\n";

    public static final String VALIDATION_REGEX =
            "^(?=.{1,200}$)(?:https?://)?([A-Za-z0-9.-]+\\.[A-Za-z]{2,})(?:/[^\\s]*)?$";

    public final String repolink;

    /**
     * Constructs a {@code RepoLink}.
     *
     * @param link A valid link.
     */
    public RepoLink(String link) {
        requireNonNull(link);
        checkArgument(isValidName(link), MESSAGE_CONSTRAINTS);
        repolink = link;
    }

    /**
     * Constructs a {@code RepoLink} that allow string value "none"
     * This Construct should only be call by fromStroage() to load RepoLink from stroage data
     *
     * @param link A valid link.
     */
    private RepoLink(String link, boolean isStorageDefault) {
        requireNonNull(link);
        if (!isStorageDefault) {
            checkArgument(isValidName(link), MESSAGE_CONSTRAINTS);
        }
        repolink = link;
    }

    /**
     * Constructs a {@code RepoLink} with default values
     */
    public RepoLink() {
        repolink = "none";
    }


    /**
     * Static method to create RepoLink from storage or external data.
     **/
    public static RepoLink fromStorage(String link) {
        requireNonNull(link);
        boolean isStorageDefault = link.equals("none");
        return new RepoLink(link, isStorageDefault);
    }

    /**
     * Returns true if a given string is a valid link.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isRepoSet() {
        return !repolink.equals("none");
    }

    @Override
    public String toString() {
        return repolink;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RepoLink)) {
            return false;
        }

        RepoLink otherRepo = (RepoLink) other;
        return repolink.equals(otherRepo.repolink);
    }

    @Override
    public int hashCode() {
        return repolink.hashCode();
    }

}
