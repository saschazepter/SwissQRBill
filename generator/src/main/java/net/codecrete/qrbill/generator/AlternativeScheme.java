//
// Swiss QR Bill Generator
// Copyright (c) 2018 Manuel Bleichenbacher
// Licensed under MIT License
// https://opensource.org/licenses/MIT
//

package net.codecrete.qrbill.generator;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Alternative payment scheme instructions.
 * <p>
 * The first word of the instruction is used as the scheme name.
 * </p>
 */
public class AlternativeScheme implements Serializable {

    private static final long serialVersionUID = -8304082204378228870L;

    /** Scheme name length */
    private int nameLength;
    /** Payment instruction */
    private String instruction;

    /**
     * Creates a new instance
     */
    public AlternativeScheme() {

    }

    /**
     * Creates an instance and sets the instruction.
     *
     * @param instruction payment instruction
     */
    public AlternativeScheme(String instruction) {
        setInstruction(instruction);
    }

    /**
     * Creates an instance and sets the instruction.
     * <p>
     * The parameter name is ignored.
     * </p>
     *
     * @deprecated Use {@link #AlternativeScheme(String)} instead.
     *
     * @param name        scheme name (ignored)
     * @param instruction payment instruction
     */
    @Deprecated
    public AlternativeScheme(String name, String instruction) {
        this(instruction);
    }

    /**
     * Gets the payment scheme name
     *
     * @return scheme name
     */
    public String getName() {
        return instruction != null ? instruction.substring(0, nameLength) : null;
    }

    /**
     * Sets the payment scheme name
     * <p>
     * This method has no effect anymore as the scheme name
     * is derived from the instruction.
     * </p>
     *
     * @deprecated The scheme name can no longer be set. It is derived from the instruction.
     *
     * @param name scheme name
     */
    @Deprecated
    public void setName(String name) {
        // not implemented anymore
    }

    /**
     * Gets the payment instruction for a given bill.
     * <p>
     * The instruction consists of a two letter abbreviation for the scheme, a separator characters
     * and a sequence of parameters (separated by the character at index 2).
     * </p>
     *
     * @return instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Gets the payment instruction for a given bill
     * <p>
     * The instruction consists of a two letter abbreviation for the scheme, a separator characters
     * and a sequence of parameters (separated by the character at index 2).
     * </p>
     *
     * @param instruction instruction
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
        this.nameLength = findSeparatorIndex(instruction);
    }

    /**
     * Gets instruction without the leading scheme name.
     *
     * @return Instruction, starting at the scheme name separator
     */
    public String getInstructionWithoutName() {
        return instruction.substring(nameLength);
    }

    private static final Pattern FIRST_WORD_PATTERN = Pattern.compile("^\\w+");

    private static int findSeparatorIndex(String instruction) {
        Matcher matcher = FIRST_WORD_PATTERN.matcher(instruction);
        return matcher.find() ? matcher.end() : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlternativeScheme that = (AlternativeScheme) o;
        return Objects.equals(instruction, that.instruction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return Objects.hash(instruction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AlternativeScheme{" +
                "instruction='" + instruction + '\'' +
                '}';
    }
}
