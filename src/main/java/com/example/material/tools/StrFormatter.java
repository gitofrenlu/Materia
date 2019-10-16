

package com.example.material.tools;


import com.example.material.utils.Func;

public class StrFormatter {
	public StrFormatter() {
	}

	public static String format(final String strPattern, final Object... argArray) {
		if (!Func.isBlank(strPattern) && !Func.isEmpty(argArray)) {
			int strPatternLength = strPattern.length();
			StringBuilder sbuf = new StringBuilder(strPatternLength + 50);
			int handledPosition = 0;

			for(int argIndex = 0; argIndex < argArray.length; ++argIndex) {
				int delimIndex = strPattern.indexOf("{}", handledPosition);
				if (delimIndex == -1) {
					if (handledPosition == 0) {
						return strPattern;
					}

					sbuf.append(strPattern, handledPosition, strPatternLength);
					return sbuf.toString();
				}

				if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == '\\') {
					if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == '\\') {
						sbuf.append(strPattern, handledPosition, delimIndex - 1);
						sbuf.append(Func.toStr(argArray[argIndex]));
						handledPosition = delimIndex + 2;
					} else {
						--argIndex;
						sbuf.append(strPattern, handledPosition, delimIndex - 1);
						sbuf.append("{");
						handledPosition = delimIndex + 1;
					}
				} else {
					sbuf.append(strPattern, handledPosition, delimIndex);
					sbuf.append(Func.toStr(argArray[argIndex]));
					handledPosition = delimIndex + 2;
				}
			}

			sbuf.append(strPattern, handledPosition, strPattern.length());
			return sbuf.toString();
		} else {
			return strPattern;
		}
	}
}
