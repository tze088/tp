package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RepoLinkTest {

    public static final RepoLink DEFAULT_REPO = new RepoLink();
    public static final RepoLink VALID_REPO = new RepoLink("https://github.com/user-name/repository-123.repo");
    public static final RepoLink VALID_DIFFERENT_REPO = new RepoLink("https://github.com/user-name/repository-x.repo");

    public static final String INVALID_REPO_STRING = "github.c";
    public static final String DEFAULT_REPO_STRING = "none";
    public static final String VALID_REPO_STRING = "https://github.com/user-name/repository-123.repo";


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RepoLink(null));
    }

    @Test
    public void constructor_invalidLink_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new RepoLink(INVALID_REPO_STRING));
    }

    @Test
    public void constructor_validLink_success() {
        assertEquals(VALID_REPO_STRING, VALID_REPO.toString());
    }

    @Test
    public void isValidName() {
        // null link
        assertThrows(NullPointerException.class, () -> RepoLink.isValidName(null));

        // invalid links
        assertFalse(RepoLink.isValidName("")); // empty string
        assertFalse(RepoLink.isValidName(" ")); // spaces only
        assertFalse(RepoLink.isValidName("https://")); // only protocol
        assertFalse(RepoLink.isValidName("https://git^hub.com/user/repo")); //special characters in domain
        assertFalse(RepoLink.isValidName("https://github.com/user^/repo")); //special characters in username
        assertFalse(RepoLink.isValidName("https://github.com/user/re^po")); //special characters in repository
        assertFalse(RepoLink.isValidName("https://github.c/user/repo")); // incorrect TLD
        assertFalse(RepoLink.isValidName("https://github.com/user/repo"
                + "c".repeat(200))); // >200 characters

        // valid links
        assertTrue(RepoLink.isValidName("https://github.com/user/repo")); // normal link
        assertTrue(RepoLink.isValidName("github.com/user/repo")); // missing protocol
        assertTrue(RepoLink.isValidName("https://github.com/u-ser/repo")); // - in username
        assertTrue(RepoLink.isValidName("https://github.com/user/repo-ro")); // - in repository
        assertTrue(RepoLink.isValidName("https://github.com/user/repo.ro")); // . in repository
        assertTrue(RepoLink.isValidName("https://github.com/user/repo_ro")); // _ in repository
        assertTrue(RepoLink.isValidName("https://github.com/user/repo"
                + "c".repeat(172))); // total 200 characters
    }

    @Test
    public void fromStorage() {
        RepoLink emptyRepoFromStorage = RepoLink.fromStorage(DEFAULT_REPO_STRING);
        RepoLink validRepoFromStorage = RepoLink.fromStorage(VALID_REPO_STRING);

        assertEquals(VALID_REPO_STRING, validRepoFromStorage.toString());
        assertEquals(DEFAULT_REPO_STRING, emptyRepoFromStorage.toString());
    }

    @Test
    public void isRepoSet() {
        assertTrue(VALID_REPO.isRepoSet());

        RepoLink emptyRepoFromStorage = RepoLink.fromStorage(DEFAULT_REPO_STRING);

        assertFalse(DEFAULT_REPO.isRepoSet());
        assertFalse(emptyRepoFromStorage.isRepoSet());

    }

    @Test
    public void equals() {

        // same values -> returns true
        assertTrue(VALID_REPO.equals(new RepoLink(VALID_REPO_STRING)));

        // same object -> returns true
        assertTrue(VALID_REPO.equals(VALID_REPO));

        // null -> returns false
        assertFalse(VALID_REPO.equals(null));

        // different types -> returns false
        assertFalse(VALID_REPO.equals(5.0f));

        // different values -> returns false
        assertFalse(VALID_REPO.equals(new RepoLink("https://github.com/user/repo_ro")));
    }
}
