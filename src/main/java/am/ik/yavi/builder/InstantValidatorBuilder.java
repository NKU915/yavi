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
package am.ik.yavi.builder;

import java.time.Instant;
import java.util.function.Function;

import am.ik.yavi.arguments.Arguments1;
import am.ik.yavi.arguments.InstantValidator;
import am.ik.yavi.constraint.InstantConstraint;
import am.ik.yavi.core.Validator;

/**
 * @since 0.10.0
 */
public class InstantValidatorBuilder {
	private final String name;

	private final Function<InstantConstraint<Arguments1<Instant>>, InstantConstraint<Arguments1<Instant>>> constraints;

	public static InstantValidatorBuilder of(String name,
			Function<InstantConstraint<Arguments1<Instant>>, InstantConstraint<Arguments1<Instant>>> constraints) {
		return new InstantValidatorBuilder(name, constraints);
	}

	InstantValidatorBuilder(String name,
			Function<InstantConstraint<Arguments1<Instant>>, InstantConstraint<Arguments1<Instant>>> constraints) {
		this.name = name;
		this.constraints = constraints;
	}

	public <T> InstantValidator<T> build(Function<? super Instant, ? extends T> mapper) {
		final Validator<Arguments1<Instant>> validator = ValidatorBuilder
				.<Arguments1<Instant>> of()
				.constraint(Arguments1::arg1, name, constraints).build();
		return new InstantValidator<>(validator, mapper::apply);
	}

	public InstantValidator<Instant> build() {
		return build(x -> x);
	}
}
