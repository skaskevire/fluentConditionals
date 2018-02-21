package com.epam.fluentConditionals.builder;

import java.util.function.Supplier;

import com.epam.fluentConditionals.state.ConditionState;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Builds conditional part of condition;
 * 
 * @author Raman_Skaskevich
 *
 */
public class WhenExpressionBuilder extends EndingExpressionBuilder{
	private ThenExpressionBuilder thenExpressionBuilder;

	public WhenExpressionBuilder(StateContext context) {
		super(context);
		thenExpressionBuilder = new ThenExpressionBuilder(context);
	}

	public ThenExpressionBuilder when(Boolean condition) {
		return when(() -> condition);
	}

	public ThenExpressionBuilder when(Supplier<Boolean> supplier) {
		ConditionState ws = new ConditionState();
		ws.setCondition(supplier);
		context.setState(ws);
		return thenExpressionBuilder;
	}
}
