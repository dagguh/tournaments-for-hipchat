package pl.dagguh.tournaments

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class PatternMatcher(private val pattern: Regex) : TypeSafeMatcher<String>() {

    override fun describeTo(description: Description) {
        description.appendText("matches regular expression=`$pattern`")
    }

    public override fun matchesSafely(string: String): Boolean {
        return string.matches(pattern)
    }

    companion object {
        fun matchesPattern(pattern: Regex): PatternMatcher {
            return PatternMatcher(pattern)
        }
    }
}