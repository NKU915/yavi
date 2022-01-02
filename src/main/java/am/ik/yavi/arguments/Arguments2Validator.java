/*
 * Copyright (C) 2018-2022 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package am.ik.yavi.arguments;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

import am.ik.yavi.core.ConstraintGroup;
import am.ik.yavi.core.ConstraintViolationsException;
import am.ik.yavi.core.Validated;
import am.ik.yavi.jsr305.Nullable;

/**
 * Generated by https://github.com/making/yavi/blob/develop/scripts/generate-args.sh
 *
 * @since 0.3.0
 */
@FunctionalInterface
public interface Arguments2Validator<A1, A2, X> {

	Validated<X> validate(@Nullable A1 a1, @Nullable A2 a2, Locale locale,
			ConstraintGroup constraintGroup);

	/**
	 * @since 0.7.0
	 */
	default <X2> Arguments2Validator<A1, A2, X2> andThen(
			Function<? super X, ? extends X2> mapper) {
		return (a1, a2, locale, constraintGroup) -> Arguments2Validator.this
				.validate(a1, a2, locale, constraintGroup).map(mapper);
	}

	/**
	 * @since 0.7.0
	 */
	default <A> Arguments1Validator<A, X> compose(
			Function<? super A, ? extends Arguments2<? extends A1, ? extends A2>> mapper) {
		return (a, locale, constraintGroup) -> {
			final Arguments2<? extends A1, ? extends A2> args = mapper.apply(a);
			return Arguments2Validator.this.validate(args.arg1(), args.arg2(), locale,
					constraintGroup);
		};
	}

	/**
	 * @since 0.10.0
	 */
	default Arguments2Validator<A1, A2, Supplier<X>> lazy() {
		// WARNING:: The default implementation is not really lazy!
		return this.andThen(x -> () -> x);
	}

	default Validated<X> validate(@Nullable A1 a1, @Nullable A2 a2) {
		return this.validate(a1, a2, Locale.getDefault(), ConstraintGroup.DEFAULT);
	}

	default Validated<X> validate(@Nullable A1 a1, @Nullable A2 a2,
			ConstraintGroup constraintGroup) {
		return this.validate(a1, a2, Locale.getDefault(), constraintGroup);
	}

	default Validated<X> validate(@Nullable A1 a1, @Nullable A2 a2, Locale locale) {
		return this.validate(a1, a2, locale, ConstraintGroup.DEFAULT);
	}

	default X validated(@Nullable A1 a1, @Nullable A2 a2)
			throws ConstraintViolationsException {
		return this.validate(a1, a2).orElseThrow(ConstraintViolationsException::new);
	}

	default X validated(@Nullable A1 a1, @Nullable A2 a2, ConstraintGroup constraintGroup)
			throws ConstraintViolationsException {
		return this.validate(a1, a2, constraintGroup)
				.orElseThrow(ConstraintViolationsException::new);
	}

	default X validated(@Nullable A1 a1, @Nullable A2 a2, Locale locale)
			throws ConstraintViolationsException {
		return this.validate(a1, a2, locale)
				.orElseThrow(ConstraintViolationsException::new);
	}

	default X validated(@Nullable A1 a1, @Nullable A2 a2, Locale locale,
			ConstraintGroup constraintGroup) throws ConstraintViolationsException {
		return this.validate(a1, a2, locale, constraintGroup)
				.orElseThrow(ConstraintViolationsException::new);
	}

}
