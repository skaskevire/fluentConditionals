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
public class SupplierExecutionState <T> implements State{

	private Supplier<T> supplier;

	public SupplierExecutionState(Supplier<T> supplier) {
		super();
		this.supplier = supplier;
	}

	@Override
	public void execute(StateContext context) {
		context.setReturnValue(supplier.get());		
		context.setState(null);
	}
}
