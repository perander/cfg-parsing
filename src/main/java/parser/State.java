package parser;

import language.Rule;

import java.util.Objects;

public class State {
    private Rule rule;
    private int dot;
    private int origin;

    public State() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return dot == state.dot &&
                origin == state.origin &&
                Objects.equals(rule, state.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rule, dot, origin);
    }

    /**
     * Create a new state.
     * @param rule
     * @param dot
     * @param origin
     */
    public State(Rule rule, int dot, int origin) {
        this.rule = rule;
        this.dot = dot;
        this.origin = origin;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public int getDot() {
        return dot;
    }

    public int getOrigin() {
        return origin;
    }

    public void incrementDot() {
        this.dot++;
    }

    public String getnextElement() {
        if (this.dot < getRule().getChild().size()) {
            return getRule().getChild().get(getDot());
        }
        return null;
    }

    /**
     * Checks whether the state is finished, so whether the state has a next element.
     * @return true if there is no next element, false otherwise
     */
    public boolean isFinished() {
        return this.getnextElement() == null;
    }

    @Override
    public String toString() {
        return "State{" +
                "rule=" + rule +
                ", dot=" + dot +
                ", origin=" + origin +
                '}';
    }
}
