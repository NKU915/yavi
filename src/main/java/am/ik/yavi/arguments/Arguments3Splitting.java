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

import am.ik.yavi.core.ValueValidator;
import am.ik.yavi.fn.Function3;
import am.ik.yavi.fn.Validations;

/**
 * Generated by https://github.com/making/yavi/blob/develop/scripts/generate-args.sh
 *
 * @since 0.7.0
 */
public class Arguments3Splitting<A1, A2, A3, R1, R2, R3> {
	protected final ValueValidator<? super A1, ? extends R1> v1;

	protected final ValueValidator<? super A2, ? extends R2> v2;

	protected final ValueValidator<? super A3, ? extends R3> v3;

	public Arguments3Splitting(ValueValidator<? super A1, ? extends R1> v1,
			ValueValidator<? super A2, ? extends R2> v2,
			ValueValidator<? super A3, ? extends R3> v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public <X> Arguments3Validator<A1, A2, A3, X> apply(
			Function3<? super R1, ? super R2, ? super R3, ? extends X> f) {
		return (a1, a2, a3, locale, constraintGroup) -> Validations.apply(f::apply,
				this.v1.validate(a1, locale, constraintGroup),
				this.v2.validate(a2, locale, constraintGroup),
				this.v3.validate(a3, locale, constraintGroup));
	}

	public <A4, R4> Arguments4Splitting<A1, A2, A3, A4, R1, R2, R3, R4> split(
			ValueValidator<? super A4, ? extends R4> v4) {
		return new Arguments4Splitting<>(v1, v2, v3, v4);
	}
}
