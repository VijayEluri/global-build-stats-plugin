package hudson.plugins.global_build_stats;

import hudson.plugins.global_build_stats.model.RegexFieldFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldFilterFactory {

	/**
	 * @deprecated Use REGEX_FIELD_FILTER_LABEL instead of this field (since v5 GlobalBuildStats file format)
	 */
	@Deprecated
	public static final String OLD_JOB_NAME_REGEX_LABEL = "jobNameRegex";
	public static final String ALL_VALUES_FILTER_LABEL = "ALL";
	public static final String REGEX_FIELD_FILTER_LABEL = "fieldRegex";
	
	private static final Pattern REGEX_FIELD_FILTER_PATTERN = Pattern.compile(REGEX_FIELD_FILTER_LABEL+"\\((.*)\\)");
	private static final Pattern ALL_VALUES_FILTER_PATTERN = Pattern.compile(ALL_VALUES_FILTER_LABEL);
	
	public static FieldFilter createFieldFilter(String fieldFilter){
		if(fieldFilter == null || ALL_VALUES_FILTER_PATTERN.matcher(fieldFilter).matches()){
			return FieldFilter.ALL;
		} else {
			Matcher fieldFilterMatcher = REGEX_FIELD_FILTER_PATTERN.matcher(fieldFilter);
			if(fieldFilterMatcher.matches()){
				return new RegexFieldFilter(fieldFilterMatcher.group(1));
			} else {
				return FieldFilter.ALL;
			}
		}
	}
}
