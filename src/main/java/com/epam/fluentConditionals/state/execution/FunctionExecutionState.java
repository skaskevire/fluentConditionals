package com.epam.fluentConditionals.state.execution;

import java.util.function.Function;

import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Implementation of {@link State}. Executes function.
 * 
 * @author Raman_Skaskevich
 *
 */
public class FunctionExecutionState<I, O> implements State {
	private Function<I, O> function;

	public FunctionExecutionState(Function<I, O> function) {
		super();
		this.function = function;
	}

	@Override
	public void execute(StateContext context) {
		context.setReturnValue(function.apply(context.getGivenParameter()));
		context.setState(null);
	}
}
