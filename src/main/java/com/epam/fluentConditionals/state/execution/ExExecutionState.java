package com.epam.fluentConditionals.state.execution;

import java.util.function.Supplier;

import com.epam.fluentConditionals.state.State;
import com.epam.fluentConditionals.state.StateContext;

/**
 * Implementation of {@link State}. Executes supplier function.
 * 
 * @author Raman_Skaskevich
 *
 */
public class ExExecutionState <T> implements State{

	private Supplier<T> supplier;

	public ExExecutionState(Supplier<T> supplier) {
		super();
		this.supplier = supplier;
	}

	@Override
	public void execute(StateContext context) {
		context.setReturnValue(supplier.get());		
		context.setState(null);
	}
}
