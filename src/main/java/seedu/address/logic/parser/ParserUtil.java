package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX =
            "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified index is invalid
     *                        (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex)
            throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code Optional<String> phone} into an {@code Phone}.
     *
     * @throws ParseException if the given {@code phone} is present but invalid.
     */
    public static Phone parsePhone(Optional<String> phone) throws ParseException {
        if (phone.isEmpty()) {
            return Phone.EMPTY_PHONE;
        } else {
            return parsePhone(phone.get());
        }
    }


    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code Optional<String> address} into an {@code Address}.
     *
     * @throws ParseException if the given {@code address} is present but invalid.
     */
    public static Address parseAddress(Optional<String> address) throws ParseException {
        if (address.isEmpty()) {
            return Address.EMPTY_ADDRESS;
        } else {
            return parseAddress(address.get());
        }
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code Optional<String> email} into an {@code Email}.
     *
     * @throws ParseException if the given {@code email} is present but invalid.
     */
    public static Email parseEmail(Optional<String> email) throws ParseException {
        if (email.isEmpty()) {
            return Email.EMPTY_EMAIL;
        } else {
            return parseEmail(email.get());
        }
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags)
            throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> names} into a {@code UniquePersonList}.
     */
    public static UniquePersonList parseContacts(Collection<String> contacts)
            throws ParseException {
        requireNonNull(contacts);
        final UniquePersonList personList = new UniquePersonList();
        for (String name : contacts) {
            Person person = new Person(
                    parseName(name), null, null, null, new HashSet<>(), UUID.randomUUID()
            );
            personList.add(person);
        }
        return personList;
    }

    /**
     * Parses {@code String date} into a {@code Date}.
     * @param date a {@String} to be parsed
     * @return {@code Date} specified the string or current time if the string is null
     * @throws ParseException if the sting is present but invalid
     */
    public static Date parseDate(String date) throws ParseException {
        if (date != null && !Date.isValidDate(date)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(date);
    }

    /**
     * Parses description string into Description.
     * @param description the string to parse
     * @return a new Description specified by the string
     * @throws ParseException if the string is present by invalid
     */
    public static Description parseDescription(String description) throws ParseException {
        if (description != null && !Description.isValidDescription(description)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }

    /**
     * Parses string into scope.
     * @param scope a string specified a certain scope
     * @return "c" if it's address book scope or "j" for journal scope
     * @throws ParseException if the string is not "c" or "j" throws exception
     */
    public static String parseScope(String scope) throws ParseException {
        requireNonNull(scope);
        String trimmedScope = scope.trim();
        if (trimmedScope.equals("c") || trimmedScope.equals("j")) {
            return trimmedScope;
        } else {
            throw new ParseException("Scope can only be \"c\" or \"j\".");
        }
    }
}
