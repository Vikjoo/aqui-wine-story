package net.spark.backend.data;

import com.vaadin.shared.util.SharedUtil;
/**
 * 
 * 
 * @author rgunoo
 *
 */
public enum PricingType {
	VAT_ENABLE, WHOLESALE, DISCOUNT_WHOLESALE;

	/**
	 * Gets a version of the enum identifier in a human friendly format.
	 *
	 * @return a human friendly version of the identifier
	 */
	public String getDisplayName() {
		return SharedUtil.upperCaseUnderscoreToHumanFriendly(name());
	}

	/**
	 * Gets a enum value for which {@link #getDisplayName()} returns the given
	 * string. Match is case-insensitive.
	 *
	 * @return the enum value with a matching display name
	 */
	public static PricingType forDisplayName(String displayName) {
		for (PricingType state : values()) {
			if (displayName.toLowerCase().equals(state.getDisplayName().toLowerCase())) {
				return state;
			}
		}
		return null;
	}
}
