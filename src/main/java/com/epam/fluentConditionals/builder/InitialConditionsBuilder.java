package com.epam.fluentConditionals.builder;

import java.util.function.Supplier;

import com.epam.fluentConditionals.state.StateContext;

/**
 * Adds initial parameter
 * 
 * @author Raman_Skaskevich
 *
 */
public class InitialConditionsBuilder extends EndingExpressionBuilder{

	public InitialConditionsBuilder(StateContext context) {
		super(context);
	}
	
	public <T> WhenExpressionBuilder given(T param) {
		return given(()->param);		
	}

	public <T> WhenExpressionBuilder given(Supplier<T> supplier) {
		context.setGivenParameter(supplier.get());
		return new WhenExpressionBuilder(context);
	}

}
