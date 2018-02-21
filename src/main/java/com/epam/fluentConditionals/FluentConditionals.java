package com.epam.fluentConditionals;

import java.util.function.Supplier;

import com.epam.fluentConditionals.builder.InitialConditionsBuilder;
import com.epam.fluentConditionals.builder.ThenExpressionBuilder;
import com.epam.fluentConditionals.builder.WhenExpressionBuilder;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Application entry point. Application structure: Nested builders to build
 * State context which contains all available states and current executing
 * state. Every state can change state of context and thereby change behavoir.
 * 
 * @author Raman_Skaskevich
 *
 */
public class FluentConditionals {
	public static <T> WhenExpressionBuilder given(T param) {
		return given(() -> param);
	}

	public static <T> WhenExpressionBuilder given(Supplier<T> supplier) {
		StateContext stateContext = new StateContext();
		InitialConditionsBuilder eicb = new InitialConditionsBuilder(stateContext);

		return eicb.given(supplier);
	}
	
	public static ThenExpressionBuilder when(Boolean condition) {
		return when(()->condition);
	}

	public static ThenExpressionBuilder when(Supplier<Boolean> supplier) {
		StateContext stateContext = new StateContext();
		WhenExpressionBuilder beb = new WhenExpressionBuilder(stateContext);

		return beb.when(supplier);
	}
	
	public static Runnable doNothing() {
		return () -> {
			System.out.println("nothing");
		};
	}
}
