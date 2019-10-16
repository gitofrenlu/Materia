
package com.example.material.tools;

import java.io.Writer;
import org.springframework.lang.Nullable;

public class FastStringWriter extends Writer {
	private StringBuilder builder;

	public FastStringWriter() {
		this.builder = new StringBuilder(64);
	}

	public FastStringWriter(final int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Negative builderfer size");
		} else {
			this.builder = new StringBuilder(capacity);
		}
	}

	public FastStringWriter(@Nullable final StringBuilder builder) {
		this.builder = builder != null ? builder : new StringBuilder(64);
	}

	public StringBuilder getBuilder() {
		return this.builder;
	}

	public void write(int c) {
		this.builder.append((char)c);
	}

	public void write(char[] cbuilder, int off, int len) {
		if (off >= 0 && off <= cbuilder.length && len >= 0 && off + len <= cbuilder.length && off + len >= 0) {
			if (len != 0) {
				this.builder.append(cbuilder, off, len);
			}
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public void write(String str) {
		this.builder.append(str);
	}

	public void write(String str, int off, int len) {
		this.builder.append(str.substring(off, off + len));
	}

	public FastStringWriter append(CharSequence csq) {
		if (csq == null) {
			this.write("null");
		} else {
			this.write(csq.toString());
		}

		return this;
	}

	public FastStringWriter append(CharSequence csq, int start, int end) {
		CharSequence cs = csq == null ? "null" : csq;
		this.write(((CharSequence)cs).subSequence(start, end).toString());
		return this;
	}

	public FastStringWriter append(char c) {
		this.write(c);
		return this;
	}

	public String toString() {
		return this.builder.toString();
	}

	public void flush() {
	}

	public void close() {
		this.builder.setLength(0);
		this.builder.trimToSize();
	}
}
